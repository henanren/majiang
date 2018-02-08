package game.boss.services;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 短信验证码 ：使用同一个签名，对同一个手机号码发送短信验证码，支持1条/分钟，累计7条/小时；
 * 短信通知： 使用同一个签名和同一个短信模板ID，对同一个手机号码发送短信通知，支持50条/日；
 * 推广短信：使用同一个签名和同一个短信模板ID，对同一个手机号码发送短信通知，支持50条/日；
 *
 * @author zuoge85@gmail.com on 2016/11/21.
 */
@Service
public class AliyunSmsService {
    private static final Logger log = LoggerFactory.getLogger(AliyunSmsService.class);

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.secret}")
    private String secret;

    @Value("${aliyun.signName}")
    private String signName;

    @Value("${aliyun.templateCode}")
    private String templateCode;

    /**
     *
     * @return 返回null 表示成功了
     */
    public String send(String phoneNumber, String code) {
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
            IAcsClient client = new DefaultAcsClient(profile);
            SingleSendSmsRequest request = new SingleSendSmsRequest();
            request.setSignName(signName);
            request.setTemplateCode(templateCode);
            request.setParamString("{\"code\":\"" + code + "\"}");
            request.setRecNum(phoneNumber);
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
            return null;
        } catch (ClientException e) {
            log.error("短信发送错误！", e);
            return e.getMessage();
        }
    }
}
