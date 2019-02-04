package cn.yufan.infomanager.dao;

import cn.yufan.infomanager.model.User;
import cn.yufan.infomanager.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImp extends BaseDao<User> implements UserDao {

	@Override
	public int save(User user) {
		String sql = "insert into users(name,password,birthday,email,phone_no,address) values(?,?,?,?,?,?)";
		return super.update(sql, user.getName(), user.getPassword(), user.getBirthday(),
				user.getEmail(), user.getPhone_no(), user.getAddress());
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from users where id = ?";
		return super.update(sql, id);
	}

	@Override
	public int update(User user) {
		String sql = "update users set name=?,password=?,birthday=?,email=?,phone_no=?,address=? where id =?";
		return super.update(sql, user.getName(), user.getPassword(), user.getBirthday(),
				user.getEmail(), user.getPhone_no(), user.getAddress(), user.getId());
	}

	@Override
	public User getById(int id) {
		String sql = "select * from users where id=?";
		return super.getFirst(sql, id);
	}

	@Override
	public List<User> getByName(String name) {
		String sql = "select * from users where name=?";
		return super.getList(sql, name);
	}



	@Override
	public List<User> fuzzyQueryByName(String name) {
		if (StringUtils.isBlank(name)) {
			return new ArrayList<>();
		} else {
			String sql = "select * from users where name like ?";
			String reg = "%" + name + "%";
			return super.getList(sql, reg);
		}
	}

	@Override
	public List<User> fuzzyQueryByPhone_no(String phone_no) {
		if (StringUtils.isBlank(phone_no)) {
			return new ArrayList<>();
		} else {
			String sql = "select * from users where phone_no like ?";
			String reg = "%" + phone_no + "%";
			return super.getList(sql, reg);
		}
	}

	@Override
	public List<User> fuzzyQueryByEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return new ArrayList<>();
		} else {
			String sql = "select * from users where email like ?";
			String reg = "%" + email + "%";
			return super.getList(sql, reg);
		}
	}

	@Override
	public List<User> getAll() {
		String sql = "select * from users";
		return super.getList(sql);
	}

	@Override
	public long countByName(String name) {
		String sql = "select count(id) from users where name = ?";
		return (long) super.getValue(sql, name);
	}

	@Override
	public User login(String name, String password) {
		String sql = "SELECT * FROM USERS WHERE NAME = ? AND PASSWORD = ?";
		return  super.getFirst(sql,name,password);
	}


}
