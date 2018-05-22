package com.chasel.blog.constant;

import com.chasel.blog.exception.BlogException;

/**
 * Lambda表达式，没有返回值
 * 
 * @author chasel
 *
 */
public interface LamCallable {

	Object run() throws BlogException;

}
