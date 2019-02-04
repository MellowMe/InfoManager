package cn.yufan.infomanager.model;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String password;
	private Date birthday;
	private String email;
	private String phone_no;
	private String address = "";


	public User(int id, String name, String password, Date birthday, String email, String phone_no, String address) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.email = email;
		this.phone_no = phone_no;
		this.address = address;
	}

	public User(String name, String password, Date birthday, String email, String phone_no, String address) {
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.email = email;
		this.phone_no = phone_no;
		this.address = address;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", birthday=" + birthday +
				", email='" + email + '\'' +
				", phone_no='" + phone_no + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
