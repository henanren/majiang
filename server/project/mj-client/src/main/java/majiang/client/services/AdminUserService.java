package majiang.client.services;

import com.isnowfox.core.PageResult;
import game.admin.PayMsg;
import game.boss.dao.dao.UserDao;
import game.boss.dao.entity.UserDO;
import majiang.client.boss.BossClient;
import majiang.client.portal.admin.model.PageModel;
import majiang.client.portal.admin.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuoge85@gmail.com on 2017/1/7.
 */
@Service
public class AdminUserService {
    private static final String salt = "nxTuij";

    @Autowired
    private UserDao userDao;
    @Autowired
    private BossClient bossClient;

    public PageModel<UserModel> getPage(int page, int pageSize) {
        PageResult<UserDO> pageResult = userDao.findPage(page, pageSize);
        return PageModel.create(pageResult, UserModel.class);
    }

    public UserDO pay(int id, int gold) throws InterruptedException {
        bossClient.writeAndFlush(new PayMsg(id, gold));
        Thread.sleep(2 * 1000);
        return userDao.get(id);
    }

    public void changeLevel(int userId, int level) {
        userDao.updatePartial(UserDO.Table.LEVEL, level, new UserDO.Key(userId));
    }
}
