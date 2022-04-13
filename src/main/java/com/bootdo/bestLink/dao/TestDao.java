package com.bootdo.bestLink.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.bestLink.domain.User;

@Mapper
public interface TestDao {
	List<User> queryUserInfo();
	
	void addUserInfo(List<User> list);
}
