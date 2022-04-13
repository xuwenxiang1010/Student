package com.bootdo.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.dao.StuCourseDao;
import com.bootdo.system.domain.CourseDO;
import com.bootdo.system.domain.SchoolDO;
import com.bootdo.system.domain.StuCourseDO;
import com.bootdo.system.domain.StuDO;
import com.bootdo.system.service.CourseService;
import com.bootdo.system.service.StuService;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/system/stu")
public class StuController extends BaseController{
	
	String prefix = "system/stu";
	
	@Autowired
	StuService stuService;
	@Autowired
	CourseService courseService;
	@Autowired
	StuCourseDao stuCourseMapper;
	
	@GetMapping()
	String stu() {
		return prefix + "/stu";
	}
	
	@ResponseBody()
	@GetMapping("/list")
	PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<StuDO> stus = stuService.list(query);
		
		List<StuDO> stusList = new ArrayList<StuDO>();
		for(StuDO stuDO : stus){
			if (stuDO.getGrade().equals("1")){
				stuDO.setGrade("大一");
		      }else if (stuDO.getGrade().equals("2")){
		    	  stuDO.setGrade("大二");
		      }else if (stuDO.getGrade().equals("3")){
		    	  stuDO.setGrade("大三");
		      }else if (stuDO.getGrade().equals("4")){
		    	  stuDO.setGrade("大四");
		      }
			 if (stuDO.getSex().equals("male")){
				  stuDO.setSex("男");
		      }else if(stuDO.getSex().equals("female")){
		    	  stuDO.setSex("女");
		      }
			stusList.add(stuDO);
		}
		
		//int total = stuService.count(query);
		PageUtils pageUtils = new PageUtils(stusList, stusList.size());
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(Model model) {
		List<CourseDO> courses = courseService.list();
		model.addAttribute("courses",courses);
		return prefix + "/add"; 
		
	}
	
	@GetMapping("/edit/{stuId}")
	String edit(@PathVariable("stuId") String stuId, Model model) {
		StuDO stuDO = stuService.get(stuId);
		model.addAttribute("stu", stuDO);
		List<CourseDO> courses = courseService.list(stuId);
		model.addAttribute("courses",courses);
		return prefix + "/edit";
	}
	
	@PostMapping("/save")
	@ResponseBody()
	R save(StuDO stu,HttpServletRequest request) {
		//String arr = request.getParameter("courseId");
		//System.out.println("========="+arr+"============");
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		stu.setStuId(getUUID());
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
		if (stuService.save(stu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@PostMapping("/update")
	@ResponseBody()
	R update(StuDO stu) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
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
		if (stuService.update(stu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@PostMapping("/remove")
	@ResponseBody()
	R remove(String stuId) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (stuService.remove(stuId) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("stuIds[]") String[] stuIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = stuService.batchremove(stuIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<SchoolDO> tree(){
		Tree<SchoolDO> tree = new Tree<SchoolDO>();
		tree = stuService.getTree();
		return tree;
	}
	
	@GetMapping("/treeView")
	String treeView() {
		return prefix + "/stuTree";
	}
}
