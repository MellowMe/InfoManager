package cn.yufan.infomanager.service;

public class ServiceFactory {
	public static UserService getUserService(){
		return new UserServiceImp();
	}
}
