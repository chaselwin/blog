package com.chasel.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chasel.blog.exception.BlogException;

/**
 * Dao层基类
 * 
 * @author chasel
 *
 * @param <T>
 */
public interface IBaseDao<T> {

	public void save(T t) throws BlogException;

	public void delete(@Param("id") long id) throws BlogException;

	public void update(T t) throws BlogException;

	public List<T> findAll(T t) throws BlogException;

	public T findById(@Param("id") long id) throws BlogException;
}
