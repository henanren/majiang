package game.boss.services;

import com.isnowfox.core.thread.FrameQueueContainer;
import com.isnowfox.util.JsonUtils;
import com.isnowfox.util.UUID;
import game.boss.ServerRuntimeException;
import game.boss.dao.dao.UserDao;
import game.boss.dao.dao.UserLoginLogDao;
import game.boss.dao.entity.UserDO;
import game.boss.dao.entity.UserLoginLogDO;
import game.boss.model.User;
import game.boss.model.WeixinUserInfo;
import game.boss.net.BossService;
import game.boss.utils.RSACoderUtils;
import mj.data.Config;
import mj.net.message.login.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zuoge85@gmail.com on 16/9/30.
 */
@Service
public class UserService extends FrameQueueContainer implements BaseService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    public static final String LOGIN_TYPE_SMS = "SMS";
    public static final String LOGIN_TYPE_ANONYMOUS = "ANONYMOUS";
    public static final String LOGIN_TYPE_TOKEN = "TOKEN";
    public static final String LOGIN_TYPE_WEIXIN = "WEIXIN";

    //    private final HashMap<String, User> openIdMap = new HashMap<>();
    private final HashMap<Integer, User> idMap = new HashMap<>();
    /**
     * 等等登陆的用户
     */
    private final HashMap<Integer, Map.Entry<Login, User>> waitMap = new HashMap<>();
//    private final HashMap<String, User> mobileMap = new HashMap<>();

    @Autowired
    private BossService bossService;
    @Autowired
    private AsyncDbService asyncDbService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserLoginLogDao userLoginLogDao;
    @Autowired
    private RoomService roomService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SettingService settingService;

    @Value("${admin.privateKey}")
    private String privateKey;

    public UserService() {
        super(FRAME_TIME_SPAN, RUN_QUEUE_MAX);
        start();
    }

    public void loginByOpenId(Login msg, User user) {
        if (msg.getType().equals(LOGIN_TYPE_SMS)) {
            smsService.check(msg.getOpenId(), msg.getCode(), checkResult -> {
                if (checkResult == null) {
                    innerLogin(msg, user);
                } else {
                    user.noticeError(checkResult);
                    user.send(new LoginError());
                }
            });
        } else if (msg.getType().equals(LOGIN_TYPE_TOKEN)) {
            innerLogin(msg, user);
        } else if (msg.getType().equals(LOGIN_TYPE_ANONYMOUS)) {
            innerLogin(msg, user);
        } else if (msg.getType().equals(LOGIN_TYPE_WEIXIN)) {
            innerLogin(msg, user);
        }
    }


    private void innerLogin(Login msg, User user) {
        asyncDbService.excuete(() -> {
            String openId = msg.getOpenId();
            UserDO userDO;
            WeixinUserInfo weixinUser = null;
            if (msg.getType().equals(LOGIN_TYPE_SMS)) {
                userDO = userDao.findObject(UserDO.Table.MOBILE, openId);
            } else if (msg.getType().equals(LOGIN_TYPE_ANONYMOUS)) {
                userDO = null;
                openId = java.util.UUID.randomUUID().toString();
            } else if (msg.getType().equals(LOGIN_TYPE_WEIXIN)) {
                try {
                    byte[] data = RSACoderUtils.decryptByPrivateKey(
                            Base64.decodeBase64(msg.getCode()), privateKey
                    );
                    weixinUser = JsonUtils.deserialize(new String(data, "utf8"), WeixinUserInfo.class);
                    openId = weixinUser.getOpenid();
                    userDO = userDao.findObject(UserDO.Table.OPEN_ID, openId);
                    if (userDO != null) {
                        userDO.setName(StringUtils.substring(weixinUser.getNickname(), 0, 8));
                        userDO.setSex(weixinUser.transformSex());
                        userDO.setAvatar(weixinUser.getHeadimgurl());
                        userDao.update(userDO);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (msg.getType().equals(LOGIN_TYPE_TOKEN)) {
                userDO = userDao.findObject(UserDO.Table.LOGIN_TOKEN, openId);
                if (userDO == null) {
                    user.send(new LoginError());
                    return;
                }
            } else {
                return;
//                userDO = userDao.findObject(UserDO.Table.OPEN_ID, openId);
            }
            if (userDO != null) {
                login(user, userDO, msg);
            } else {
                //创建用户
                try {
                    userDO = new UserDO();
                    userDO.setOpenId(openId);
                    userDO.setPassword(RandomStringUtils.randomAlphanumeric(11));
                    userDO.setName(StringUtils.substring(openId, 0, 8));
                    userDO.setUuid(UUID.generateNoSep());
                    Date now = new Date();
                    userDO.setUpdateDate(now);
                    userDO.setCreateDate(now);
                    userDO.setGold(settingService.getSetting().getInitGold());

                    if (msg.getType().equals(LOGIN_TYPE_SMS)) {
                        userDO.setMobile(openId);
                    } else if (msg.getType().equals(LOGIN_TYPE_WEIXIN) && weixinUser != null) {
                        userDO.setName(weixinUser.getNickname());
                        userDO.setSex(weixinUser.transformSex());
                        userDO.setAvatar(weixinUser.getHeadimgurl());
                    }

                    int id = (int) userDao.insert(userDO);
                    userDO.setId(id);
                    login(user, userDO, msg);
                } catch (DuplicateKeyException ex) {
                    log.error("重复的注册!" + openId, ex);
                    userDO = userDao.findObject(UserDO.Table.UNIQUE_OPEN_ID, openId);
                    if (userDO == null) {
                        //
                        log.error("重复注册后检查登录失败!关闭连接" + openId, ex);
                        user.close();
                    } else {
                        login(user, userDO, msg);
                    }
                }
            }
        });
    }

    public void logout(User user) {
        run(() -> {
            {
                User prevUser = idMap.get(user.getUserId());
                if (prevUser == user) {
                    closeUser(user);
                } else {
                    log.error("严重错误,出现不一致,{} <--> {}", user, prevUser);
                    //关闭两个用户
                    closeUser(user);
                    closeUser(prevUser);
                }
            }
            {
                Map.Entry<Login, User> loginUserEntry = waitMap.get(user.getUserId());
                if (loginUserEntry != null && loginUserEntry.getValue() != user) {
                    waitMap.remove(user.getUserId());
                    login(loginUserEntry.getValue(), loginUserEntry.getValue().getUserDO(), loginUserEntry.getKey());
                }
            }
        });
    }

    private void closeUser(User user) {
        if (frameThread != Thread.currentThread()) {
            throw new ServerRuntimeException("只能在指定的线程调用");
        }
        if (user == null) {
            return;
        }
        UserDO userDO = user.getUserDO();
        if (userDO == null) {
            return;
        }
//        openIdMap.remove(userDO.getOpenId());
        idMap.remove(userDO.getId());
        roomService.checkOffline(user);
//        mobileMap.remove(userDO.getMobile());

        UserLoginLogDO loginLog = user.getLoginLog();
        Date now = new Date();
        if (loginLog != null) {
            asyncDbService.excuete(user, () -> {
                if (loginLog.getId() > 0) {
                    userLoginLogDao.updatePartial(
                            UserLoginLogDO.Table.LOGOUT_DATE, now, loginLog.getKey()
                    );
                }
            });
        }
    }

    public void handlerUser(int userId, Consumer<User> callback) {
        run(() -> {
            User user = idMap.get(userId);
            callback.accept(user);
        });
    }


    public void minusGold(int userId, Config config) {
        run(() -> {
            int max = config.getInt(Config.CHAPTER_MAX);
            int gold = (int) Math.ceil(max / 8);
            User user = idMap.get(userId);
            if (user != null) {
                UserDO userDO = user.getUserDO();
                userDO.setGold(userDO.getGold() - gold);
                user.send(new Pay(-gold));
            }
            asyncDbService.excuete(userId, () -> {
                userDao.incrementUpdatePartial(
                        UserDO.Table.GOLD, -gold,
                        new UserDO.Key(userId)
                );
            });
        });
    }

    public void pay(int userId, int gold) {
        run(() -> {
            User user = idMap.get(userId);
            if (user != null) {
                UserDO userDO = user.getUserDO();
                userDO.setGold(userDO.getGold() + gold);
                userDO.setHistoryGold(userDO.getHistoryGold() + gold);
                user.send(new Pay(gold));
            }
            asyncDbService.excuete(userId, () -> {
                userDao.incrementUpdatePartial(
                        UserDO.Table.GOLD, gold,
                        UserDO.Table.HISTORY_GOLD, gold,
                        new UserDO.Key(userId)
                );
            });
        });
    }


    private void login(User user, UserDO userDO, Login msg) {
        run(() -> {
            {
                User prevUser = idMap.get(userDO.getId());
                if (prevUser != null) {
                    //重复登录,关闭连接
                    prevUser.send(new RepeatLoginRet());
                    prevUser.close();
                    Map.Entry<Login, User> loginUserEntry = waitMap.get(userDO.getId());
                    if (loginUserEntry != null) {
                        loginUserEntry.getValue().close();
                    }
                    user.setUserDO(userDO);
                    waitMap.put(userDO.getId(), new AbstractMap.SimpleImmutableEntry<Login, User>(msg, user));
                    return;
                }
            }
//            openIdMap.put(userDO.getOpenId(), user);
            idMap.put(userDO.getId(), user);
//            mobileMap.put(userDO.getMobile(), user);
            userDO.setLoginToken(java.util.UUID.randomUUID().toString());
            userDO.setIp(user.getIp());
            userDO.setLongitude(NumberUtils.toDouble(msg.getLongitude()));
            userDO.setLatitude(NumberUtils.toDouble(msg.getLatitude()));

            user.setUserDO(userDO);
            UserLoginLogDO loginLog = new UserLoginLogDO();
            loginLog.setUserId(userDO.getId());
            loginLog.setLoginDate(new Date());
            loginLog.setIp(user.getIp());
            loginLog.setLoginToken(userDO.getLoginToken());
            user.setLoginLog(loginLog);

            asyncDbService.excuete(user, () -> {
                //登录记录
                long id = userLoginLogDao.insert(loginLog);
                loginLog.setId((int) id);

                Map<String, Object> m = new HashMap<>();
                m.put(UserDO.Table.LOGIN_TOKEN, userDO.getLoginToken());
                m.put(UserDO.Table.IP, userDO.getIp());
                m.put(UserDO.Table.LONGITUDE, userDO.getLongitude());
                m.put(UserDO.Table.LATITUDE, userDO.getLatitude());
                userDao.updatePartial(
                        m, userDO.getKey()
                );
            });

            //发回登录成功消息
            LoginRet msgRet = new LoginRet(
                    userDO.getId(),
                    userDO.getName(),
                    userDO.getOpenId(),
                    userDO.getUuid(),
                    userDO.getAvatar(),
                    userDO.getSex(), null, userDO.getGold(), userDO.getLoginToken(),
                    userDO.getIp()
            );

            roomService.checkUserRoom(user, checkId -> {
                msgRet.setRoomCheckId(checkId);
                user.send(settingService.getSettingMsg());
                user.send(msgRet);
            });
        });
    }


    @Override
    protected void threadMethod(int frameCount, long time, long passedTime) {

    }

    @Override
    protected void errorHandler(Throwable e) {
        log.error("严重异常", e);
    }

}
