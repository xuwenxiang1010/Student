package com.bootdo.student.course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.student.course.pojo.CourseDO;

@Mapper
public interface CourseDao {
	
	CourseDO get(String courseId);
	
	List<CourseDO> list(Map<String,Object> map);

	int save(CourseDO cus);
	
	int update(CourseDO cus);
	
	int remove(String courseId);
	
	int batchRemove(String[] courseIds);
	
	int count(Map<String,Object> map);
	
	
}
