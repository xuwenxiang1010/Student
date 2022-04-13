package com.bootdo.system.service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.SchoolDO;


public interface SchService {
	
	SchoolDO get(Long schId);
	
	List<SchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolDO sch);
	
	int update(SchoolDO sch);
	
	int remove(Long schId);
	
	Tree<SchoolDO> getTree();
	
	boolean checkSchHasStu(Long schId);
	
	List<Long> listChildrenIds(Long parentId);

}
