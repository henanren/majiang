package majiang.client.portal.agent.model;


import org.forkjoin.apikit.core.Message;

/**
 * @author zuoge85 on 15/6/17.
 */
@Message
public class AgentLoginTokenModel {
    private String token;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginTokenModel{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
