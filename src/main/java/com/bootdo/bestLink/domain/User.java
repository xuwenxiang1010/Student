package com.bootdo.bestLink.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class User {
	
	@Excel(name = "学号", orderNum = "0")
	private Integer id;
	
	@Excel(name = "姓名", orderNum = "1")
	private String userName;
	
	@Excel(name = "年龄", orderNum = "2")
	private int userAge;
	
	@Excel(name = "邮箱", orderNum = "3")
	private String userEmail;

	public User() {
		
	}
	
	public User(Integer id, String userName, int userAge, String userEmail) {
		this.id = id;
		this.userName = userName;
		this.userAge = userAge;
		this.userEmail = userEmail;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userAge=" + userAge + ", userEmail=" + userEmail + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
