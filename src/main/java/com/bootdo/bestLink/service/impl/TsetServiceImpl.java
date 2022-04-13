package com.bootdo.bestLink.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.bestLink.dao.TestDao;
import com.bootdo.bestLink.domain.User;
import com.bootdo.bestLink.service.TestService;

@Service
public class TsetServiceImpl implements TestService{

	@Autowired
	TestDao userMapper;
	
	@Override
	public List<User> queryUserInfo() {
		return userMapper.queryUserInfo();
	}

	@Override
	public void addUserInfo(List<User> list) {
		userMapper.addUserInfo(list);
	}
	
}
