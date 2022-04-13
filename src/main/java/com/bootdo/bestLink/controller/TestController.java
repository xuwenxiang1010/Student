package com.bootdo.bestLink.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.bestLink.domain.User;
import com.bootdo.bestLink.service.impl.TsetServiceImpl;
import com.bootdo.bestLink.util.ExcelUtil;

@RestController
public class TestController {
	@Autowired
	TsetServiceImpl testService;
	
	@RequestMapping("exportExcel")
	public void export(HttpServletResponse response) {
		List<User> userList = testService.queryUserInfo();
		ExcelUtil.exportExcel(userList,"用户信息","sheet1",User.class,"testDATA.xls",response);
	}
	
}
