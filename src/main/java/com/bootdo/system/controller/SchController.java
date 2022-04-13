package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.SchoolDO;
import com.bootdo.system.service.SchService;


@Controller
@RequestMapping("/system/sch")
public class SchController extends BaseController {
	private String prefix = "system/sch";
	
	@Autowired
	private SchService schService;
	
	@GetMapping()
	String sch(){
		return prefix + "/sch";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public List<SchoolDO> list(){
		Map<String, Object> query = new HashMap<>(16);
		List<SchoolDO> schList = schService.list(query);
		return schList;
	}
	
	@GetMapping("/add/{pId}")
	String add(@PathVariable("pId") Long pId,Model model){
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "学院");
		} else {
			model.addAttribute("pName", schService.get(pId).getSchName());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{schId}")
	String edit(@PathVariable("schId") Long schId, Model model){
		SchoolDO sch = schService.get(schId);
		model.addAttribute("sch", sch);
		if(Constant.DEPT_ROOT_ID.equals(sch.getParentId())) {
			model.addAttribute("parentSchName", "无");
		}else {
			SchoolDO parSch = schService.get(sch.getParentId());
			model.addAttribute("parentSchName", parSch.getSchName());
		}
		return  prefix + "/edit";
	}
	
	@ResponseBody
	@PostMapping("/save")
	public R save(SchoolDO sch) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (schService.save(sch) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public R update(SchoolDO sch) {
		if (schService.update(sch) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@ResponseBody
	@PostMapping("/remove")
	public R remove(Long schId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", schId);
		if(schService.count(map)>0) {
			return R.error(1, "包含下级部门,不允许修改");
		}
		if (schService.checkSchHasStu(schId)) {
			if (schService.remove(schId) > 0) {
				return R.ok();
			}
		}else {
			return R.error(1,"包含学生，不允许修改");
		}
		return R.error();
	}
	

	@GetMapping("/tree")
	@ResponseBody
	public Tree<SchoolDO> tree() {
		Tree<SchoolDO> tree = new Tree<SchoolDO>();
		tree = schService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/schTree";
	}

}	
	

