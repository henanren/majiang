package majiang.client.portal.agent.form;

import org.forkjoin.apikit.core.Message;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 */
@Message
public class AgentLoginForm {
    private int id;
    @NotEmpty
    @Length(min = 4, max = 32)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AgentLoginForm{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
