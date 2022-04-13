package com.bootdo.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.system.domain.SchoolDO;

@Mapper
public interface SchDao {
	SchoolDO get(Long schId);
	
	List<SchoolDO> list(Map<String,Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolDO sch);
	
	int update(SchoolDO sch);
	
	int remove(Long schId);
	
	Long[] listParentSch();
	
	int getSchStuNumber(Long schId);
}
