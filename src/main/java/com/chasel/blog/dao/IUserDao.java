package com.chasel.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chasel.blog.vo.User;
import com.chasel.blog.vo.UserAuth;

@Mapper
public interface IUserDao extends IBaseDao<User> {

	public UserAuth login(@Param("identityType") String identityType, @Param("identifier") String identifier);

	public void saveUserAuth(UserAuth userAuth);

	public User queryUserByAccountAndNickName(@Param("account")String account,@Param("nickName") String nickName);

	public void delAuthByUserId(@Param("userId") long userId);

	public void updateAuthCredential(@Param("userId") Long userId,@Param("identityType") String identityType, @Param("identifier") String identifier, @Param("credential") String credential);

	public List<UserAuth> queryAuthByUserId(Long userId);

}
