package majiang.client.portal.admin.form;

import org.forkjoin.apikit.core.Message;

/**
 *
 */
@Message
public class PayForm {
    private int id;
    private int gold;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
