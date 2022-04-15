package com.bootdo.student.student.dao;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Mapper;

import com.bootdo.student.student.pojo.entity.StuDO;

@Mapper
public interface StuDao{
	
	StuDO get(String stuId);
	
	List<StuDO> list(Map<String,Object> map);
	
	int save(StuDO stu);
	
	int update(StuDO stu);
	
	int remove(String stuId);
	
	int batchRemove(String[] stuIds);

	int count(Map<String,Object> map);
	
	Long[] listAllSch();
}
