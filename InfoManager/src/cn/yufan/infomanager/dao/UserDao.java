package cn.yufan.infomanager.dao;

import cn.yufan.infomanager.model.User;

import java.util.List;

public interface UserDao {

	int save(User user);

	int deleteById(int id);

	int update(User user);

	User getById(int id);

	List<User> getByName(String name);

	List<User> fuzzyQueryByName(String reg);

	List<User> fuzzyQueryByPhone_no(String reg);

	List<User> fuzzyQueryByEmail(String reg);

	List<User> getAll();

	long countByName(String name);

	User login(String name, String password);
}
