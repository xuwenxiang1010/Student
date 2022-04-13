package com.bootdo.bestLink.service;

import java.util.List;

import com.bootdo.bestLink.domain.User;

public interface TestService {
	
	List<User> queryUserInfo();
	
	void addUserInfo(List<User> list);
}
