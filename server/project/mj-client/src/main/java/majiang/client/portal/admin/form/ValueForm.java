package majiang.client.portal.admin.form;

import org.forkjoin.apikit.core.Message;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 */
@Message
public class ValueForm {
    @NotEmpty
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueForm{" +
                "value='" + value + '\'' +
                '}';
    }
}
