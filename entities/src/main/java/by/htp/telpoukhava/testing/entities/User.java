package by.htp.telpoukhava.testing.entities;

import by.htp.telpoukhava.testing.abst.Entity;

/**Class describes user, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/

public class User extends Entity {
	private int userId;
	private String name;
	private String surname;
	private String login;
	private String password;
	private boolean access;

	public User() {

	}

	public User(int userId, String name, String surname, String login, String password, boolean access) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.access = access;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccess() {
		return access;
	}

	public void setAccess(boolean access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
				+ password + ", access=" + access + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userId != user.userId) return false;
		if (access != user.access) return false;
		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		return password != null ? password.equals(user.password) : user.password == null;

	}

	@Override
	public int hashCode() {
		int result = userId;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (surname != null ? surname.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (access ? 1 : 0);
		return result;
	}
}
