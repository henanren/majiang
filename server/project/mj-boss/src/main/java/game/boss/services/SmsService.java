package game.boss.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * TODO 这个地方其实就是有状态了，接收的人请修改！
 *
 * @author zuoge85 on 15/5/11.
 */
@Service
public class SmsService {
    public static final String TEST_NUMBER_START = "110";


    @Value("${sms.checkNums}")
    private int checkNums;

    @Value("${sms.expireMinutes}")
    private int expireMinutes;

    @Value("${sms.codeLength}")
    private int codeLength;

    @Autowired
    private AliyunSmsService aliyunSmsService;

    @Autowired
    private AsyncService asyncService;


    private Cache<String, AuthCode> codeCache;

    public SmsService() {
    }

    @PostConstruct
    private void initCache() {
        codeCache = CacheBuilder.newBuilder()
                .expireAfterWrite(expireMinutes, TimeUnit.MINUTES).build();
    }

    /**
     * 发送验证码
     */
    public void authCode(String phoneNumber, Consumer<String> callback) {
        // 测试账号
        if (phoneNumber.startsWith(TEST_NUMBER_START)) {
            asyncService.excuete(() -> callback.accept(null));
            return;
        }
        String code = RandomStringUtils.randomNumeric(codeLength);
        puCache(phoneNumber, code);
        asyncService.excuete(() -> callback.accept(aliyunSmsService.send(phoneNumber, code)));
    }

    private void puCache(String phoneNumber, String code) {
        codeCache.put(phoneNumber, new AuthCode(code));
    }


    public void check(String phone, String code, Consumer<String> callback) {
        asyncService.excuete(() -> {
            String result = innerCheck(phone, code);
            callback.accept(result);
        });
    }

    /**
     * @return 返回null 表示成功
     */
    private String innerCheck(String phone, String code) {
        if (phone.startsWith(TEST_NUMBER_START)) {
            return null;
        }
        AuthCode anthCode = codeCache.getIfPresent(phone);
        if (anthCode != null) {
            synchronized (anthCode) {
                int accessNums = anthCode.accessNums.incrementAndGet();
                if (accessNums < checkNums) {
                    if (anthCode.code.equals(code)) {
                        codeCache.invalidate(phone);
                        return null;
                    }
                    return "验证码错误";
                }
                codeCache.invalidate(phone);
                return "验证码尝试次数超过checkNums!";
            }
        } else {
            return "验证码失效或者不存在!";
        }
    }


    public int getCheckNums() {
        return checkNums;
    }


    public static class AuthCode {
        private String code;
        private AtomicInteger accessNums = new AtomicInteger(0);

        public AuthCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "AuthCode{" + "code=" + code + ", accessNums=" + accessNums
                    + '}';
        }
    }
}