package game.boss.dao.dao;

import  org.forkjoin.core.dao.impi.BaseDaoImpi;
import game.boss.dao.entity.UserLoginLogDO;

import org.springframework.stereotype.Component;

@Component
public class UserLoginLogDao extends BaseDaoImpi<UserLoginLogDO,UserLoginLogDO.Key>  {
	public UserLoginLogDao() {
		super(UserLoginLogDO.TABLE_INFO);
	}

	public UserLoginLogDO get(int id) {
		return get(new UserLoginLogDO.Key(id));
	}
}
