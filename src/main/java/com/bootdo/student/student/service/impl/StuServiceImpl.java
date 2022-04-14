package com.bootdo.student.student.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.student.school.dao.SchDao;
import com.bootdo.student.student.dao.StuCourseDao;
import com.bootdo.student.student.dao.StuDao;
import com.bootdo.student.school.pojo.SchoolDO;
import com.bootdo.student.student.pojo.entity.StuCourseDO;
import com.bootdo.student.student.pojo.entity.StuDO;
import com.bootdo.student.school.service.SchService;
import com.bootdo.student.student.service.StuService;


@Service
public class StuServiceImpl implements StuService {
	
    @Autowired
    StuDao stuMapper;
    @Autowired
    SchDao schMapper;
	@Autowired
	SchService schService;
	@Autowired
	StuCourseDao stuCourseMapper;
    
	@Override
	public StuDO get(String stuId) {
		List<String> courseIds = stuCourseMapper.listCourseId(stuId);
		StuDO stu = stuMapper.get(stuId);
		stu.setSchName(schMapper.get(stu.getSchId()).getSchName());
		stu.setCourseIds(courseIds);
		return stu;
	}

	
	@Override
	public List<StuDO> list(Map<String, Object> map) {
		String schId = map.get("schId").toString();
		if (StringUtils.isNotBlank(schId)) {
			Long schIdl = Long.valueOf(schId);
			List<Long> childIds = schService.listChildrenIds(schIdl);
			childIds.add(schIdl);
			map.put("schId", null);
			map.put("schIds", childIds);
		}
		return stuMapper.list(map);
	}


	@Transactional
	@Override
	public int save(StuDO stu) {
		int count = stuMapper.save(stu);
		String stuId = stu.getStuId();
		List<String> courses = stu.getCourseIds();
		stuCourseMapper.removeByStuId(stuId);
		List<StuCourseDO> list = new ArrayList<>();
		for(String courseId : courses) {
			StuCourseDO sc = new StuCourseDO();
			sc.setStuId(stuId);
			sc.setCourseId(courseId);
			list.add(sc);
			stu.setCourseIdNum(list.size());
		}
		stuCourseMapper.removeByStuId(stuId);
		if(list.size() > 0) {
			stuCourseMapper.batchSave(list);
		}
		return count;
	}

	@Override
	public int update(StuDO stu) {
		int count = stuMapper.update(stu);
		String stuId = stu.getStuId();
		List<String> courseIds = stu.getCourseIds();
		stuCourseMapper.removeByStuId(stuId);
		List<StuCourseDO> list = new ArrayList<>();
		for(String courseId : courseIds) {
			StuCourseDO sc = new StuCourseDO();
			sc.setStuId(stuId);
			sc.setCourseId(courseId);
			list.add(sc);
			stu.setCourseIdNum(list.size());
		}
		if(list.size() > 0) {
			stuCourseMapper.batchSave(list);
		}
		return count;
	}

	@Override
	public int remove(String stuId) {
		stuCourseMapper.remove(stuId);
		return stuMapper.remove(stuId);
	}
	
	@Override
	public Set<String> listCourses(String stuId) {
		return null;
	}
	
    @Transactional
	@Override
	public int batchremove(String[] stuIds) {
		int count = stuMapper.batchRemove(stuIds);
		stuCourseMapper.batchRemoveByStuId(stuIds);
		return count;
	}

	@Override
	public int count(Map<String, Object> map) {
		return stuMapper.count(map);
	}


	@Override
	public Tree<SchoolDO> getTree() {
		List<Tree<SchoolDO>> trees = new ArrayList<Tree<SchoolDO>>();
		List<SchoolDO> schs = schMapper.list(new HashMap<String, Object>(16));
		Long[] pSchs = schMapper.listParentSch();
		Long[] uSchs = stuMapper.listAllSch();
		Long[] allSchs = (Long[]) ArrayUtils.addAll(pSchs, uSchs);
		for (SchoolDO sch : schs) {
			if (!ArrayUtils.contains(allSchs, sch.getSchId())) {
				continue;
			}
			Tree<SchoolDO> tree = new Tree<SchoolDO>();
			tree.setId(sch.getSchId().toString());
			tree.setParentId(sch.getParentId().toString());
			tree.setText(sch.getSchName());
			Map<String, Object> state = new HashMap<>();
			state.put("opened", true);
			state.put("mType", "sch");
			tree.setState(state);
			trees.add(tree);
		}
		List<StuDO> stus = stuMapper.list(new HashMap<String, Object>(16));
		for(StuDO stu : stus) {
			Tree<SchoolDO> tree = new Tree<SchoolDO>();
			tree.setId(stu.getStuId().toString());
			tree.setParentId(stu.getSchId().toString());
			tree.setText(stu.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
            state.put("mType", "stu");
            tree.setState(state);
            trees.add(tree);
		}
		Tree<SchoolDO> t = BuildTree.build(trees);
		return t;
		
	}

	
	
}
