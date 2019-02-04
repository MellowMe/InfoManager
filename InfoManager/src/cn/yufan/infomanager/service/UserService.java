package cn.yufan.infomanager.service;

import cn.yufan.infomanager.model.User;

import java.util.List;

public interface UserService {
	List<User> mixedFuzzyQuery(String nameReg, String telReg, String emailReg);

	List<User> getAll();

	int add(User user);

	long countByName(String name);

	User getById(int id);

	int update(User user);

	int deleteById(int id);

	User login(String name, String password);
}