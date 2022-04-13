package com.bootdo.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.CourseDO;
import com.bootdo.system.service.CourseService;

@Controller
@RequestMapping("/system/course")
public class CourseController extends BaseController{
	
	String prefix = "system/cus";
	@Autowired
	CourseService courseService;
	
	@GetMapping()
	String cus() {
		return prefix + "/cus";
	}
	
	@ResponseBody()
	@GetMapping("/list")
	List<CourseDO> list() {
		List<CourseDO> cus = courseService.list();
		List<CourseDO> cusList = new ArrayList<CourseDO>();
		for(CourseDO courseDO : cus){
			if (courseDO.getCourseType().equals("1")){
				courseDO.setCourseType("公共课");
		      }else if (courseDO.getCourseType().equals("2")){
		    	  courseDO.setCourseType("选修课");
		      }else if (courseDO.getCourseType().equals("3")){
		    	  courseDO.setCourseType("体育课");
		      }else if (courseDO.getCourseType().equals("4")){
		    	  courseDO.setCourseType("专业课");
		      }
			 
			cusList.add(courseDO);
		}
		
		return cusList;
	}
	
	
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}
	
	@GetMapping("/edit/{courseId}")
	String edit(@PathVariable("courseId") String courseId, Model model) {
		CourseDO courseDO = courseService.get(courseId);
		model.addAttribute("course",courseDO);
		return prefix + "/edit";
	}
	
	@PostMapping("/save")
	@ResponseBody()
	R save(CourseDO cus) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		cus.setCourseId(getUUID());
		if (courseService.save(cus) > 0) {
			return R.ok();
		}else {
			return R.error(1,"保存失败");
		}
	}
	
	@PostMapping("/update")
	@ResponseBody()
	R update(CourseDO cus) {
		if(Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (courseService.update(cus) > 0) {
			return R.ok();
		}else {
			return R.error(1,"保存失败");
		}
	}
	
	@PostMapping("/remove")
	@ResponseBody()
	R remove(String courseId) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (courseService.remove(courseId) > 0) {
			return R.ok();
		}else {
			return R.error(1,"删除失败");
		}
		
	}
	
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("courseIds[]") String[] courseIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = courseService.batchRemove(courseIds);
		if(r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
