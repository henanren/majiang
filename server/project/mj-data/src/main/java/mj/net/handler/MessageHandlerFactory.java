package mj.net.handler;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;


public final class MessageHandlerFactory implements ApplicationContextAware {
	private static final MessageHandlerFactory instance = new MessageHandlerFactory();
	public static final MessageHandlerFactory getInstance(){
		return instance;
	}
	
	private ApplicationContext applicationContext;
	
	private final MessageHandler<?,?>[][] array;
	
	private MessageHandlerFactory(){
		array = new MessageHandler<?,?>[8][];
	}

	@PostConstruct
    public void init(){

		array[1] = new MessageHandler[24];

		array[1][1] = getBean(mj.net.handler.game.GameChapterStartHandler.class);

		array[1][6] = getBean(mj.net.handler.game.GameJoinRoomHandler.class);

		array[1][11] = getBean(mj.net.handler.game.OperationCPGHRetHandler.class);

		array[1][13] = getBean(mj.net.handler.game.OperationFaPaiRetHandler.class);

		array[1][15] = getBean(mj.net.handler.game.OperationOutRetHandler.class);

		array[1][22] = getBean(mj.net.handler.game.VoteDelSelectRetHandler.class);

		array[1][23] = getBean(mj.net.handler.game.VoteDelStartHandler.class);

		array[7] = new MessageHandler[25];

		array[7][0] = getBean(mj.net.handler.login.CreateRoomHandler.class);

		array[7][2] = getBean(mj.net.handler.login.DelRoomHandler.class);

		array[7][4] = getBean(mj.net.handler.login.ExitRoomHandler.class);

		array[7][6] = getBean(mj.net.handler.login.JoinRoomHandler.class);

		array[7][8] = getBean(mj.net.handler.login.LoginHandler.class);

		array[7][14] = getBean(mj.net.handler.login.PingHandler.class);

		array[7][19] = getBean(mj.net.handler.login.RoomHistoryListHandler.class);

		array[7][21] = getBean(mj.net.handler.login.SendAuthCodeHandler.class);
    }
	public MessageHandler<?,?> getHandler(int type, int id){
		return array[type][id];
	}

	@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private <T> T getBean(Class<T> cls) {
        try {
            return applicationContext.getBean(cls);
        } catch (NoSuchBeanDefinitionException ex) {
            return null;
        }
    }
}
