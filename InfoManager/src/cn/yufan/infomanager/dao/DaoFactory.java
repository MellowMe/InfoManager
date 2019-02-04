package cn.yufan.infomanager.dao;

public class DaoFactory {
	public static UserDao getUserDao(){
		return new UserDaoImp();
	}
}
