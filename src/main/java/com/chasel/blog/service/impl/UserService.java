package com.chasel.blog.service.impl;

import static com.chasel.blog.constant.MessagesConstant.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chasel.blog.constant.CodeConstants;
import com.chasel.blog.constant.EIdentityType;
import com.chasel.blog.dao.IUserDao;
import com.chasel.blog.exception.BlogException;
import com.chasel.blog.service.IUserService;
import com.chasel.blog.util.ResourceUtil;
import com.chasel.blog.vo.User;
import com.chasel.blog.vo.UserAuth;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserService extends BaseService implements IUserService {

	private static final String MSG_ID_NULL = "找不到该用户ID";
	private static final String MSG_USER_NULL = "参数值为空";
	private static final String MSG_LOGIN_NULL = "账号或密码不能为空";
	private static final String MSG_NICK_NAME_NULL = "昵称不能为空";
	private static final String MSG_EXIT_ACCOUNT_NULL = "账号已存在";
	private static final String MSG_EXIT_NICK_NULL = "昵称已存在";
	private static final String DONT_UPDATE = "不能修改他人信息";
	public static final String USER_NAME = "userName";

	@Autowired
	private IUserDao userDao;

	@Override
	public void save(User user) throws BlogException {
		// 1-> 校验vo参数
		if (user == null) throw new BlogException(CodeConstants.ERR_CODE_99, MSG_USER_NULL);
		
		// 2-> 校验账号密码
		if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) 
			throw new BlogException(CodeConstants.ERR_CODE_99, MSG_LOGIN_NULL);
		
		// 3-> 校验昵称不能为空
		if (StringUtils.isEmpty(user.getNickName())) throw new BlogException(CodeConstants.ERR_CODE_99, MSG_NICK_NAME_NULL);
		
		// 4-> 校验是否存在账号或昵称
		User queryUser = userDao.queryUserByAccountAndNickName(user.getAccount(), null);
		if (null != queryUser) throw new BlogException(CodeConstants.ERR_CODE_99, MSG_EXIT_ACCOUNT_NULL);
		
		User queryUserv = userDao.queryUserByAccountAndNickName(null, user.getNickName());
		if (null != queryUserv) throw new BlogException(CodeConstants.ERR_CODE_99, MSG_EXIT_NICK_NULL);
		
		// 4-> 默认普通用户
		user.setRole(0);
		
		// 5-> save User table
		userDao.save(user);
		
		// 6-> 根据实际情况save User_Auth table
		userDao.saveUserAuth(new UserAuth(user.getId(), EIdentityType.ACCOUNT.getString(), user.getAccount(), user.getPassword()));
		
		if (!StringUtils.isEmpty(user.getPhone())) 
			userDao.saveUserAuth(new UserAuth(user.getId(), EIdentityType.PHONE.getString(), user.getPhone(), user.getPassword()));
		
		if (!StringUtils.isEmpty(user.getEmail())) 
			userDao.saveUserAuth(new UserAuth(user.getId(), EIdentityType.EMAIL.getString(), user.getEmail(), user.getPassword()));
	}

	@Override
	public void delete(long id) throws BlogException {
		User user = findById(id);
		if (user == null) {
			throw new BlogException(CodeConstants.ERR_CODE_99, MSG_ID_NULL);
		} else {
			userDao.delete(id);
			userDao.delAuthByUserId(id);
		}
	}

	@Override
	public void update(User user) throws BlogException {
		if (user == null||(StringUtils.isEmpty(user.getPassword())
				&&StringUtils.isEmpty(user.getPhone())&&StringUtils.isEmpty(user.getEmail()))
				||user.getId() == null) {
			throw new BlogException(CodeConstants.ERR_CODE_99, MSG_USER_NULL);
		} else {
			Long userId = ResourceUtil.getSessionName();
			User userv = findById(user.getId());
			if (userId == userv.getId()) {
				userDao.update(user);
				if (!StringUtils.isEmpty(user.getPassword())) {
					List<UserAuth> authList = userDao.queryAuthByUserId(user.getId());
					authList.forEach(auth -> {
						if (auth.getIdentityType().equalsIgnoreCase(EIdentityType.ACCOUNT.getString())) {
							userDao.updateAuthCredential(user.getId(), auth.getIdentityType(), null, user.getPassword());
						}
						if (auth.getIdentityType().equalsIgnoreCase(EIdentityType.PHONE.getString())) {
							if (!StringUtils.isEmpty(user.getPhone())) userDao.updateAuthCredential(user.getId(), auth.getIdentityType(), user.getPhone(), user.getPassword());
						}
						if (auth.getIdentityType().equalsIgnoreCase(EIdentityType.EMAIL.getString())) {
							if (!StringUtils.isEmpty(user.getEmail())) userDao.updateAuthCredential(user.getId(), auth.getIdentityType(), user.getEmail(), user.getPassword());
						}
					});
				}
			}else{
				throw new BlogException(CodeConstants.ERR_CODE_99, DONT_UPDATE);
			}
		}
	}

	@Override
	public PageInfo<User> findAll(User user, PageInfo<User> pageInfo) throws BlogException {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<User> list = userDao.findAll(user);
		return new PageInfo<User>(list);
	}

	@Override
	public User findById(long id) throws BlogException {
		return userDao.findById(id);
	}

	@Override
	public String login(String identityType, String identifier,String credential, HttpSession httpSession) throws BlogException {
		// 1-> query table user_auth
		UserAuth userAuth = null;
		if (StringUtils.isEmpty(identityType)){
			identityType=EIdentityType.ACCOUNT.getString();
			userAuth = userDao.login(identityType, identifier);
			
			if (userAuth == null) {
				identityType=EIdentityType.PHONE.getString();
				userAuth = userDao.login(identityType, identifier);
			}
			
			if (userAuth == null) {
				identityType=EIdentityType.EMAIL.getString();
				userAuth = userDao.login(identityType, identifier);
			}
		}else{
			userAuth = userDao.login(identityType, identifier);
		}
		if (userAuth == null || !userAuth.getCredential().equals(credential)) throw new BlogException(ACCONT_FAIL);
		// 2-> query table user by userId
		User user = findById(userAuth.getUserId());
		
		// 3-> set Session value
		httpSession.setAttribute(USER_NAME, user.getId());
		return user.getNickName();
	}

	@Override
	public String isLogin(HttpSession httpSession) throws BlogException {
		// 1-> Get session value
		Long userId = (Long) httpSession.getAttribute(USER_NAME);
		
		// 2-> Query table User
		if (StringUtils.isEmpty(userId)) {
			throw new BlogException(getMassage(NOT_LOGIN));
		}else{
			User user = findById(userId);
			return user.getNickName();
		}
	}

	@Override
	public void logout(HttpSession httpSession) {
		httpSession.removeAttribute(USER_NAME);
	}

}
