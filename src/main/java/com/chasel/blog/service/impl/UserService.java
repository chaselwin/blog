package com.chasel.blog.service.impl;

import static com.chasel.blog.constant.MessagesConstant.ACCONT_FAIL;
import static com.chasel.blog.constant.MessagesConstant.NOT_LOGIN;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chasel.blog.constant.CodeConstants;
import com.chasel.blog.dao.IUserDao;
import com.chasel.blog.exception.BlogException;
import com.chasel.blog.service.IUserService;
import com.chasel.blog.vo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserService extends BaseService implements IUserService {

	private static final String MSG_ID_NULL = "找不到该用户ID";
	private static final String MSG_USER_NULL = "该用户信息不存在";
	//private static final String DONT_UPDATE = "不能修改他人账号密码";
	public static final String USER_NAME = "userName";

	@Autowired
	private IUserDao userDao;

	@Override
	public void save(User user) throws BlogException {
		if (user == null) {
			throw new BlogException(CodeConstants.ERR_CODE_99, MSG_USER_NULL);
		}
		userDao.save(user);
	}

	@Override
	public void delete(int id) throws BlogException {
		if (findById(id) == null) {
			throw new BlogException(CodeConstants.ERR_CODE_99, MSG_ID_NULL);
		} else {
			userDao.delete(id);
		}
	}

	@Override
	public void update(User user) throws BlogException {
		if (user == null||(StringUtils.isEmpty(user.getPassword())&&StringUtils.isEmpty(user.getName()))) {
			throw new BlogException(CodeConstants.ERR_CODE_99, MSG_USER_NULL);
		} else {
			/*String userName = ResourceUtil.getSessionName();
			User userv = findById(user.getId());
			if (userName.equalsIgnoreCase(userv.getName())) {
				userDao.update(user);
			}else{
				throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, DONT_UPDATE);
			}*/
			userDao.update(user);
		}
	}

	@Override
	public PageInfo<User> findAll(User user, PageInfo<User> pageInfo) throws BlogException {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<User> list = userDao.findAll(user);
		return new PageInfo<User>(list);
	}

	@Override
	public User findById(int id) throws BlogException {
		return userDao.findById(id);
	}

	@Override
	public String login(String account, String password, HttpSession httpSession) throws BlogException {
		User newUser = userDao.login(account, password);
		if (newUser == null) {
			throw new BlogException(ACCONT_FAIL);
		}
		httpSession.setAttribute(USER_NAME, newUser.getId()+"");
		return newUser.getName();
	}

	@Override
	public String isLogin(HttpSession httpSession) throws BlogException {
		String userId = (String) httpSession.getAttribute(USER_NAME);
		if (StringUtils.isEmpty(userId)) {
			throw new BlogException(getMassage(NOT_LOGIN));
		}else{
			User user = findById(Integer.parseInt(userId));
			return user.getName();
		}
	}

	@Override
	public void logout(HttpSession httpSession) {
		httpSession.removeAttribute(USER_NAME);
	}

}
