package com.bootdo.student.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.student.course.dao.CourseDao;
import com.bootdo.student.student.dao.StuCourseDao;
import com.bootdo.student.student.dao.StuDao;
import com.bootdo.student.course.pojo.CourseDO;
import com.bootdo.student.course.service.CourseService;


@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseDao courseMapper;
	@Autowired
	StuCourseDao stuCourseMapper;
	@Autowired
	StuDao stuMapper;
	
	@Override
	public CourseDO get(String courseId) {
		CourseDO courseDO = courseMapper.get(courseId);
		return courseDO;
	}

	@Override
	public List<CourseDO> list() {
		List<CourseDO> cus = courseMapper.list(new HashMap<>(50));
		return cus;
	}
	
	
	@Transactional
	@Override
	public int save(CourseDO cus) {
		int c = courseMapper.save(cus);
		return c;
	}

	@Override
	public int update(CourseDO cus) {
		int c = courseMapper.update(cus);
		return c;
	}

	@Override
	public int remove(String courseId) {
		int c = courseMapper.remove(courseId);
		return c;
	}

	@Override
	public int batchRemove(String[] courseIds) {
		int c = courseMapper.batchRemove(courseIds);
		stuCourseMapper.batchRemove(courseIds);
		return c;
	}
	
	@Override
	public int count(Map<String, Object> map) {
		return courseMapper.count(map);
	}


	@Override
	public List<CourseDO> list(String stuId) {
		List<String> courseIds = stuCourseMapper.listCourseId(stuId);
		List<CourseDO> courses = courseMapper.list(new HashMap<>(50));
		for(CourseDO course : courses) {
			course.setCourseSign("false");
			for(String courseId : courseIds) {
				if (Objects.equals(course.getCourseId(), courseId)) {
					course.setCourseSign("true");
					break;
				}
			}
		}
		return courses;
	}


}
