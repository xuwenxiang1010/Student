package com.bootdo.system.domain;

import java.util.List;

/*
 * 学生管理
 * 
 *@Description todo
 *@author xuwenxiang
 *@date 2021年9月18日下午2:26:21
 */

public class GradeDO {

	//年级
	private String grade;
	//年级
	private String gradeName;
	public String getGrade() {
		return grade;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	public GradeDO(){
		
	}
}
