package majiang.client.services;

import com.isnowfox.core.PageResult;
import game.boss.dao.dao.RoomDao;
import game.boss.dao.entity.RoomDO;
import majiang.client.boss.BossClient;
import majiang.client.portal.admin.model.PageModel;
import majiang.client.portal.admin.model.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuoge85@gmail.com on 2017/1/7.
 */
@Service
public class AdminRoomService {
    private static final String salt = "nxTuij";

    @Autowired
    private RoomDao roomDao;
    @Autowired
    private BossClient bossClient;

    public PageModel<RoomModel> getPage(int page, int pageSize) {
        PageResult<RoomDO> pageResult = roomDao.findPage(page, pageSize);
        return PageModel.create(pageResult, RoomModel.class);
    }
}
