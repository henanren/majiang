package majiang.client.portal.admin.form;

import org.forkjoin.apikit.core.Message;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 */
@Message
public class LoginForm {
    @NotEmpty
    @Length(min = 4, max = 32)
    private String name;
    @NotEmpty
    @Length(min = 4, max = 32)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
