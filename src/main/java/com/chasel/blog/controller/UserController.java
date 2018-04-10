package com.chasel.blog.controller;

import static com.chasel.blog.constant.MessagesConstant.ADD_FAIL;
import static com.chasel.blog.constant.MessagesConstant.ADD_SUCCESS;
import static com.chasel.blog.constant.MessagesConstant.DEL_FAIL;
import static com.chasel.blog.constant.MessagesConstant.DEL_SUCCESS;
import static com.chasel.blog.constant.MessagesConstant.QUERY_FAIL;
import static com.chasel.blog.constant.MessagesConstant.QUERY_SUCCESS;
import static com.chasel.blog.constant.MessagesConstant.UPDATE_FAIL;
import static com.chasel.blog.constant.MessagesConstant.UPDATE_SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chasel.blog.constant.BaseController;
import com.chasel.blog.constant.ResponseResult;
import com.chasel.blog.service.IUserService;
import com.chasel.blog.vo.User;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("用户信息API")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@ApiOperation("用户分页查询")
	@RequestMapping(path = "/query/page/{pageSize}/{pageNum}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseResult findAll(@RequestBody User user, PageInfo<User> pageInfo) {

		return value(() -> {return userService.findAll(user, pageInfo);},QUERY_SUCCESS,QUERY_FAIL);
	}

	/**
	 * 添加用户
	 * 
	 */
	@ApiOperation("用户注册")
	@RequestMapping(path = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseResult save(@RequestBody User user) {

		return processz(() -> {userService.save(user);}, ADD_SUCCESS, ADD_FAIL);
	}

	/**
	 * 根据id查询用户
	 * 
	 */
	@ApiOperation("根据ID查询用户")
	@RequestMapping(path = "/findUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseResult findById(@PathVariable Long id) {

		return value(() -> {return userService.findById(id);}, QUERY_SUCCESS, QUERY_FAIL);
	}

	/**
	 * 删除用户
	 * 
	 */
	@ApiOperation("根据ID删除用户")
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseResult delete(@PathVariable Long id) {

		return process(() -> {userService.delete(id);}, DEL_SUCCESS, DEL_FAIL);
	}

	/**
	 * 编辑用户
	 * 
	 */
	@ApiOperation("编辑用户:根据用户ID编辑用户密码或者名字")
	@RequestMapping(path = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseResult update(@RequestBody User user) {

		return process(() -> {userService.update(user);}, UPDATE_SUCCESS, UPDATE_FAIL);
	}

}
