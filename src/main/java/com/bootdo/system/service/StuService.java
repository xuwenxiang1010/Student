package com.bootdo.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.SchoolDO;
import com.bootdo.system.domain.StuDO;

@Service
public interface StuService {
	
	StuDO get(String stuId);
	
	List<StuDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(StuDO stu);
//	
	int update(StuDO stu);
//	
	int remove(String stuId);
//	
	int batchremove(String[] stuIds);
	
	Tree<SchoolDO> getTree();
	Set<String> listCourses(String stuId);
}
