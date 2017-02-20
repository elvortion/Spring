package com.smilegate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id @GeneratedValue	// GeneratedVaue = Auto Increment
	private long id; // 변경할 수 없는 고유 값. Primary Key	

	@Column(length = 15, nullable = false, unique = true)
	private String userId;
	
	@Column(length = 15, nullable = false)
	private String password;
	
	@Column(length = 15, nullable = false)
	private String name;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	public User() {
		
	}

	public User(String userId, String password, String name, String email) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public String toString() {
		return "ID : " + Long.toString(this.id) + " | USER_ID : " + this.userId + " | PW : " + this.password + " | NAME : " + this.name + " | EMAIL : " + this.email;
	}
	
	public boolean isMatchPassword(String password) {
		return this.password.equals(password);
	}
	
	public boolean isMatchId(long id) {
		return this.id == id;
	}
	
	public void update(User updatedUserData) {
		if (isMatchPassword(updatedUserData.password)) {
			this.name = updatedUserData.name;
			this.email = updatedUserData.email;
			// 비밀번호는 변경 불가
			// this.password = updatedUserData.password;
		}	
	}

	public User getUserData() {
		return this;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
