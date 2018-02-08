package game.boss.net;

import com.isnowfox.core.net.Session;
import com.isnowfox.core.net.message.Message;
import com.isnowfox.core.net.message.MessageFactory;
import com.isnowfox.game.proxy.message.LogoutPxMsg;
import com.isnowfox.game.proxy.message.PxMsg;
import com.isnowfox.game.proxy.message.SinglePxMsg;
import game.boss.ServerRuntimeException;
import game.boss.model.User;
import game.boss.msg.*;
import game.boss.services.AsyncService;
import game.boss.services.RoomService;
import game.boss.services.SettingService;
import game.boss.services.UserService;
import mj.net.handler.MessageHandler;
import mj.net.handler.MessageHandlerFactory;
import mj.net.message.login.Login;
import mj.net.message.login.Ping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zuoge85@gmail.com on 16/9/27.
 */
public class MessageManager {
    private static final Logger log = LoggerFactory.getLogger(MessageManager.class);

    @Autowired
    private MessageFactory messageFactory;
    @Autowired
    private GatewayManager gatewayManager;
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private MessageHandlerFactory messageHandlerFactory;
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SettingService settingService;

    void handlerGatewayMessage(PxMsg msg) {
        short sessionId = -1;
        try {
            if (msg instanceof RegGatewayMsg) {
                RegGatewayMsg regGatewayMsg = (RegGatewayMsg) msg;
                log.info("注册网关", regGatewayMsg);
                Gateway gateway = gatewayManager.reg(msg.getSession().channel, regGatewayMsg.getGatewayId());
                if (gateway != null) {
                    msg.getSession().set(gateway);
                }
            } else if (msg instanceof RegSessionMsg) {
                final Gateway gateway = checkGatway(msg);
                final RegSessionMsg regSessionMsg = (RegSessionMsg) msg;
                sessionId = regSessionMsg.getSessionId();

                if (gateway.getSession(sessionId) != null) {
                    log.error("错误消息:{}重复登陆！");
                    return;
                }
                gateway.reg(sessionId, regSessionMsg.getIp());
            } else if (msg instanceof SinglePxMsg) {
                final Gateway gateway = checkGatway(msg);

                SinglePxMsg fm = (SinglePxMsg) msg;
                sessionId = fm.getSessionId();
                Session<UserImpi> userSession = gateway.getSession(sessionId);
                if (userSession == null) {
                    log.error("错误消息:{}没有注册的用户！", sessionId);
                    throw new ServerRuntimeException("没有注册的用户" + sessionId);
                }
                final Message rawMsg = fm.toMessage(messageFactory);
                rawMsg.setSession(userSession);
                handlerMessage(rawMsg, userSession.get());
            } else if (msg instanceof LogoutPxMsg) {
                LogoutPxMsg lm = (LogoutPxMsg) msg;
                sessionId = lm.getSessionId();
                Gateway gateway = checkGatway(msg);
                gateway.unReg(lm.getSessionId());
            } else if (msg instanceof JoinRoomMsg) {
                JoinRoomMsg lm = (JoinRoomMsg) msg;

                final Gateway gateway = checkGatway(msg);
                sessionId = lm.getSessionId();
                Session<UserImpi> userSession = gateway.getSession(sessionId);
                if (userSession == null) {
                    log.error("错误消息:{}没有注册的用户！", sessionId);
                    throw new ServerRuntimeException("没有注册的用户" + sessionId);
                }
                UserImpi userImpi = userSession.get();
                if (userImpi == null) {
                    log.error("错误消息:{}不存在用户！", sessionId);
                    return;
                }
                roomService.joinRoomGatewaySuccess(userImpi);
            } else if (msg instanceof ExitRoomMsg) {
                ExitRoomMsg lm = (ExitRoomMsg) msg;

                final Gateway gateway = checkGatway(msg);
                sessionId = lm.getSessionId();
                Session<UserImpi> userSession = gateway.getSession(sessionId);
                if (userSession == null) {
                    log.error("错误消息:{}没有注册的用户！", sessionId);
                    throw new ServerRuntimeException("没有注册的用户" + sessionId);
                }
                UserImpi userImpi = userSession.get();
                if (userImpi == null) {
                    log.error("错误消息:{}不存在用户！", sessionId);
                    return;
                }
                roomService.exitRoomGatewaySuccess(userImpi);
            } else if (msg instanceof DelRoomMsg) {
                DelRoomMsg lm = (DelRoomMsg) msg;

                final Gateway gateway = checkGatway(msg);
                sessionId = lm.getSessionId();
                Session<UserImpi> userSession = gateway.getSession(sessionId);
                if (userSession == null) {
                    log.error("错误消息:{}没有注册的用户！", sessionId);
                    return;
                }
                UserImpi userImpi = userSession.get();
                if (userImpi == null) {
                    log.error("错误消息:{}不存在用户！", sessionId);
                    return;
                }
                roomService.delRoomGatewaySuccess(userImpi);
            }
        } catch (Throwable th) {
            log.error("严重消息错误 " + msg, th);
            Gateway gateway = (Gateway) msg.getSession().get();
            if (sessionId > -1 && gateway != null) {
//                Net.getInstance().closeSession(sessionId, SystemErrorCode.PROTOCAL_ERROR);
                log.error("踢掉session:" + sessionId);
                gateway.unReg(sessionId);
            }
        }
    }

    private Gateway checkGatway(PxMsg msg) {
        Session<Gateway> gatewaySession = msg.getSession();
        final Gateway gateway = gatewaySession.get();
        if (gateway == null) {
            log.error("未注册的网关:{},gatewaySession:{}", msg, gatewaySession);
            throw new ServerRuntimeException("未注册的网关:" + msg + ",gatewaySession:" + gatewaySession);
        }
        return gateway;
    }

    /**
     * 消息处理机制
     */
    @SuppressWarnings("unchecked")
    private void handlerMessage(Message message, User user) {
        if (!(message instanceof Ping)) {
            log.info("收到消息:{}", message);
        }
        MessageHandler handler = messageHandlerFactory.getHandler(
                message.getMessageType(), message.getMessageId()
        );

        if (handler != null) {
            if (Login.TYPE == message.getMessageType() && Login.ID == message.getMessageId()) {
                handler.handler(message, user);
            } else {
                if (user.getUserDO() == null) {
                    if (!(message instanceof Ping)) {
                        log.info("还未登录!:{}", message);
                    }
                }
//                else {
                asyncService.excuete(handler, message, user);
//                }
            }
        } else {
            log.info("没有处理器的消息:{}", message);
        }
    }
}
