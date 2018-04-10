package com.chasel.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chasel.blog.vo.User;
import com.chasel.blog.vo.UserAuth;

@Mapper
public interface IUserDao extends IBaseDao<User> {

	public UserAuth login(@Param("identityType") String identityType, @Param("identifier") String identifier);

	public void saveUserAuth(UserAuth userAuth);

	public User queryUserByAccountAndNickName(@Param("account")String account,@Param("nickName") String nickName);

}
