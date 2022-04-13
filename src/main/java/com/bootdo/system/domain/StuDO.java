package com.bootdo.system.domain;

import java.util.List;

/*
 * 学生管理
 * 
 *@Description todo
 *@author xuwenxiang
 *@date 2021年9月18日下午2:26:21
 */

public class StuDO {
	
	//学生ID
	private String stuId;
	//学生姓名
	private String name;
	//学生年龄
	private int age;
	//电话号码
	private Long mobile;
	//学号
	private String stuNo;
	//年级
	private String grade;
	//性别
	private String sex;
	//邮箱
	private String email;
	//部门
	private Long schId;
	private String schName;
	//课程
	private List<String> courseIds;
	private int courseIdNum;
	
	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getSchId() {
		return schId;
	}
	public void setSchId(Long schId) {
		this.schId = schId;
	}
	public String getSchName() {
		return schName;
	}
	public void setSchName(String schName) {
		this.schName = schName;
	}
	public List<String> getCourseIds() {
		return courseIds;
	}
	public void setCourseIds(List<String> courseIds) {
		this.courseIds = courseIds;
	}
	public int getCourseIdNum() {
		return courseIdNum;
	}
	public void setCourseIdNum(int courseIdNum) {
		this.courseIdNum = courseIdNum;
	}
	
	
	
	
	
}
