package com.bjsxt.mapper;

import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.User;

public interface UserMapper {
	@Select("select * from user where uname=#{uname} and pwd=#{pwd}")
	User selByUserPwd(User user);
}
