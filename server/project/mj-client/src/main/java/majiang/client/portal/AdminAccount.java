package majiang.client.portal;


import game.boss.dao.entity.AdminDO;

/**
 * 账户信息
 */
public class AdminAccount implements AccountObject {
    public AdminAccount(AdminDO adminDO) {
        this.adminDO = adminDO;
    }

    private AdminDO adminDO;

    public AdminDO getAdminDO() {
        return adminDO;
    }

    public void setAdminDO(AdminDO adminDO) {
        this.adminDO = adminDO;
    }

    @Override
    public String toString() {
        return "AdminAccount{" +
                "adminDO=" + adminDO +
                '}';
    }

    public String getName() {
        return adminDO.getName();
    }

    public String getToken() {
        return adminDO.getToken();
    }
}
