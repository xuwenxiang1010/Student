package com.bootdo.student.student.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.student.student.pojo.entity.StuDO;

/*
 * 学生管理
 * 
 *@Description todo
 *@author xuwenxiang
 *@date 2021年9月18日下午10:38:58
 */
@Mapper
public interface StuDao {
	
	StuDO get(String stuId);
	
	List<StuDO> list(Map<String,Object> map);
	
	int save(StuDO stu);
	
	int update(StuDO stu);
	
	int remove(String stuId);
	
	int batchRemove(String[] stuIds);

	int count(Map<String,Object> map);
	
	Long[] listAllSch();
}
