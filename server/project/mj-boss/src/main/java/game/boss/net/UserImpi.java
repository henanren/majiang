package game.boss.net;

import com.isnowfox.core.net.message.Message;
import com.isnowfox.game.proxy.message.PxMsg;
import game.boss.model.User;

/**
 * @author zuoge85@gmail.com on 16/9/26.
 */
 class UserImpi extends User {
    private Gateway gateway;

    void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    Gateway getGateway() {
        return gateway;
    }

    public void send(Message msg){
        gateway.send(getSessionId(), msg);
    }

    @Override
    public void sendToGateway(PxMsg msg) {
        gateway.sendToGateway(msg);
    }


    public void close(){
        gateway.unReg(getSessionId());
    };
}
