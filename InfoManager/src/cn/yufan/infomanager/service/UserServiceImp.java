package cn.yufan.infomanager.service;

import cn.yufan.infomanager.dao.DaoFactory;
import cn.yufan.infomanager.dao.UserDao;
import cn.yufan.infomanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserService{
	private UserDao userDao = DaoFactory.getUserDao();
	@Override
	public List<User> mixedFuzzyQuery(String name, String phone_no, String email) {
			List<User> list = new ArrayList<>();
			list.addAll(userDao.fuzzyQueryByName(name));
			list.addAll(userDao.fuzzyQueryByPhone_no(phone_no));
			list.addAll(userDao.fuzzyQueryByEmail(email));
			return list;
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public int add(User user) {
		return userDao.save(user);
	}

	@Override
	public long countByName(String name) {
		return userDao.countByName(name);
	}

	@Override
	public User getById(int id) {
		return userDao.getById(id);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int deleteById(int id) {
		return userDao.deleteById(id);
	}

	@Override
	public User login(String name, String password) {
		return userDao.login(name,password);
	}
}
