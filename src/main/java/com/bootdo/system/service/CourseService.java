package com.bootdo.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bootdo.system.domain.CourseDO;

@Service
public interface CourseService {
	CourseDO get(String courseId);
	
	List<CourseDO> list();
	
	int count(Map<String, Object> map);
	
	int save(CourseDO cus);
	
	int update(CourseDO cus);
	
	int remove(String courseId);
	
	int batchRemove(String[] courseIds);

	
	List<CourseDO> list(String stuId);

}
