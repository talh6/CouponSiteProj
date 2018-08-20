package com.tal.couponsproj.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.tal.couponsproj.enums.loginType;

@XmlRootElement
public class LoginInput {
	private String username;
	private String password;
	private loginType type;
	
	public LoginInput() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public loginType getType() {
		return type;
	}

	public void setType(loginType type) {
		this.type = type;
	}
}
