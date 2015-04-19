package minhang.entity;

import java.io.Serializable;

/**
 * The persistent class for the user database table.
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userid;

	private String description;

	private int gender;

	private String password;

	private int permission;

	private int state;

	private String username;

	public User() {
	}

	public User(String userid, String username, String password,
			String description, int gender, int permission, int state) {
		super();
		this.userid = userid;
		this.description = description;
		this.gender = gender;
		this.password = password;
		this.permission = permission;
		this.state = state;
		this.username = username;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}