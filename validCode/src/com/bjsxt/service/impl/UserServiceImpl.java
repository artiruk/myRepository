package com.bjsxt.service.impl;

import com.bjsxt.mapper.UserMapper;
import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;

public class UserServiceImpl implements UserService{
	private UserMapper um; 
	public UserMapper getUm() {
		return um;
	}
	public void setUm(UserMapper um) {
		this.um = um;
	}
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return um.selByUserPwd(user);
	}

}
