package com.bootdo.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.SchDao;
import com.bootdo.system.domain.SchoolDO;
import com.bootdo.system.service.SchService;


@Service
public class SchServiceImpl implements SchService{
	@Autowired
	private SchDao schMapper;

	@Override
	public SchoolDO get(Long schId) {
		return schMapper.get(schId);
	}

	@Override
	public List<SchoolDO> list(Map<String, Object> map) {
		return schMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return schMapper.count(map);
	}

	@Override
	public int save(SchoolDO sch) {
		return schMapper.save(sch);
	}

	@Override
	public int update(SchoolDO sch) {
		return schMapper.update(sch);
	}

	@Override
	public int remove(Long schId) {
		return schMapper.remove(schId);
	}

	@Override
	public Tree<SchoolDO> getTree() {
		List<Tree<SchoolDO>> trees = new ArrayList<Tree<SchoolDO>>();
		List<SchoolDO> schs = schMapper.list(new HashMap<String, Object>(16));
		for(SchoolDO sch : schs){
			Tree<SchoolDO> tree = new Tree<SchoolDO>();
            tree.setId(sch.getSchId().toString());
            tree.setParentId(sch.getParentId().toString());
            tree.setText(sch.getSchName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
		}
		Tree<SchoolDO> t = BuildTree.build(trees);
        return t;
	}


	@Override
	public List<Long> listChildrenIds(Long parentId) {
		List<SchoolDO> schDOS = list(null);
        return treeMenuList(schDOS, parentId);
	}
	
	List<Long> treeMenuList(List<SchoolDO> menuList, long pid) {
        List<Long> childIds = new ArrayList<>();
        for (SchoolDO mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getParentId() == pid) {
                //递归遍历下一级
                treeMenuList(menuList, mu.getSchId());
                childIds.add(mu.getSchId());
            }
        }
        return childIds;
    }

	@Override
	public boolean checkSchHasStu(Long schId) {
		int result = schMapper.getSchStuNumber(schId);
		return result == 0 ? true : false;
	}
	
}
