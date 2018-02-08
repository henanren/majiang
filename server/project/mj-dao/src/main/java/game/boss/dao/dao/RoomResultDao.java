package game.boss.dao.dao;

import  org.forkjoin.core.dao.impi.BaseDaoImpi;
import game.boss.dao.entity.RoomResultDO;

import org.springframework.stereotype.Component;

@Component
public class RoomResultDao extends BaseDaoImpi<RoomResultDO,RoomResultDO.Key>  {
	public RoomResultDao() {
		super(RoomResultDO.TABLE_INFO);
	}

	public RoomResultDO get(int id) {
		return get(new RoomResultDO.Key(id));
	}
}
