package com.bootdo.student.course.pojo;


/*
 * 学生课程表
 * 
 */

public class CourseDO{
	
	
	//主键
	private String courseId;
	
	//课程名称
	private String courseName;
	
	//课程类型
	private String courseType;
	
	//课程描述
	private String courseSign;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseSign() {
		return courseSign;
	}

	public void setCourseSign(String courseSign) {
		this.courseSign = courseSign;
	}

	

	
	
	
}
