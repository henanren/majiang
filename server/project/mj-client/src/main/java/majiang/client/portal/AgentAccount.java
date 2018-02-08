package majiang.client.portal;


import game.boss.dao.entity.UserAgentTokenDO;
import game.boss.dao.entity.UserDO;

/**
 * 账户信息
 */
public class AgentAccount implements AccountObject {
    private UserDO userDO;
    private UserAgentTokenDO userAgentTokenDO;


    public AgentAccount(UserDO userDO, UserAgentTokenDO userAgentTokenDO) {
        this.userDO = userDO;
        this.userAgentTokenDO = userAgentTokenDO;
    }

    public UserAgentTokenDO getUserAgentTokenDO() {
        return userAgentTokenDO;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    @Override
    public String toString() {
        return "AgentAccount{" +
                "userDO=" + userDO +
                ", userAgentTokenDO=" + userAgentTokenDO +
                '}';
    }
}
