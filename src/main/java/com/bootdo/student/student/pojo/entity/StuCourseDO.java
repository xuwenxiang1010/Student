package com.bootdo.student.student.pojo.entity;

public class StuCourseDO {
	private String id;
	
	private String stuId;
	
	private String courseId;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "StuCourseDO [id=" + id + ", stuId=" + stuId + ", courseId=" + courseId + "]";
	}

	
	
}
