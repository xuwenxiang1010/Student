package com.bootdo.common.controller;

import java.util.UUID;

import com.bootdo.system.domain.UserToken;

import org.springframework.stereotype.Controller;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
	/**
	 * 获取主键UUID
	 *@Description todo
	 *@author xuwenxiang
	 *@date 2021年9月22日下午4:35:55
	 *@return String
	 *
	 */
	public String  getUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}