package com.chasel.blog.vo;

import java.io.Serializable;
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String account;
	private String password;
	private String name;

	public int getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", name=" + name + "]";
	}

	

}
