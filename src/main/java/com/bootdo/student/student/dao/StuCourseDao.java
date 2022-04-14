package com.bootdo.student.student.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.student.student.pojo.entity.StuCourseDO;

/**
 * 学生课程
 * @author xwx
 *
 */
@Mapper
public interface StuCourseDao {

	StuCourseDO get(String id);
	
	List<StuCourseDO> list(Map<String,Object> map);
	
	int count(Map<String, Object> map);

	int save(StuCourseDO stuCourse);

	int update(StuCourseDO stuCourse);

	int remove(String id);

	int batchRemove(String[] ids);

	List<String> listCourseId(String stuId);

	int removeByStuId(String stuId);

	int removeByCourseId(String courseId);

	int batchSave(List<StuCourseDO> list);

	int batchRemoveByStuId(String[] ids);
}
