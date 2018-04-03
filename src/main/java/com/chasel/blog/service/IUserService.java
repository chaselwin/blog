package com.chasel.blog.service;

import javax.servlet.http.HttpSession;

import com.chasel.blog.exception.BlogException;
import com.chasel.blog.vo.User;
import com.github.pagehelper.PageInfo;

public interface IUserService extends IBaseService<User> {

	public String login(String account, String password, HttpSession httpSession) throws BlogException;

	public PageInfo<User> findAll(User user, PageInfo<User> pageInfo) throws BlogException;

	public void save(User user) throws BlogException;

	public void delete(int id) throws BlogException;

	public void update(User user) throws BlogException;

	public User findById(int id) throws BlogException;

	public String isLogin(HttpSession httpSession) throws BlogException;

	public void logout(HttpSession httpSession);
}
