package com.chasel.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chasel.blog.vo.User;

@Mapper
public interface IUserDao extends IBaseDao<User> {

	public User login(@Param("account") String account, @Param("password") String password);
}
