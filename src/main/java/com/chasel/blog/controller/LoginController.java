package com.chasel.blog.controller;

import static com.chasel.blog.constant.MessagesConstant.ACCONT_SUCCESS;
import static com.chasel.blog.constant.MessagesConstant.ADD_FAIL;
import static com.chasel.blog.constant.MessagesConstant.HAS_LOGIN;
import static com.chasel.blog.constant.MessagesConstant.LOGOUT_SUCCESS;
import static com.chasel.blog.constant.MessagesConstant.NOT_LOGIN;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chasel.blog.constant.BaseController;
import com.chasel.blog.constant.ResponseResult;
import com.chasel.blog.service.IUserService;
import com.chasel.blog.vo.UserAuth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("登录信息")
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 登录
	 * 
	 * @param user
	 * @param httpSession
	 * @return
	 * @throws JSONException
	 */
	@ApiOperation("用户登录")
	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseResult login(@RequestBody UserAuth userAuth, HttpSession httpSession) throws JSONException {

		return valuez(
				() -> userService.login(
						userAuth.getIdentityType(), 
						userAuth.getIdentifier(),
						userAuth.getCredential(),
						httpSession),
				ACCONT_SUCCESS,
				ADD_FAIL
				);
	}

	/**
	 * 登录状态
	 * 
	 * @param httpSession
	 * @return
	 * @throws JSONException
	 */
	@ApiOperation("是否登录")
	@RequestMapping(path = "/checkLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseResult isLogin(HttpSession httpSession) throws JSONException {

		return valuez(() -> userService.isLogin(httpSession),HAS_LOGIN, NOT_LOGIN);
	}

	/**
	 * 注销
	 * 
	 * @param httpSession
	 * @return
	 * @throws JSONException
	 */
	@ApiOperation("注销登录")
	@RequestMapping(path = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseResult logout(HttpSession httpSession) throws JSONException {

		return processz(() -> userService.logout(httpSession),LOGOUT_SUCCESS,LOGOUT_SUCCESS);
	}

}
