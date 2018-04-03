package com.chasel.blog.constant;

import java.io.Serializable;

public class MessagesConstant implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//项目结构变动需要改动
	public static final String EXECUTION="execution(public * com.chasel.blog.controller.*.*(..))";
	public static final String BASEPACKGE="com.chasel.blog";
	public static final String SWAGGER_URL="http://localhost:8080/girl/swagger-ui.html";

	public static final String GIRL = "girl";

	public static final String ADD_SUCCESS = "add.success";

	public static final String ADD_FAIL = "add.fail";

	public static final String DEL_SUCCESS = "del.success";

	public static final String DEL_FAIL = "del.fail";

	public static final String UPDATE_SUCCESS = "update.success";

	public static final String UPDATE_FAIL = "update.fail";

	public static final String QUERY_SUCCESS = "query.success";

	public static final String QUERY_FAIL = "query.fail";

	public static final String ACCONT_FAIL = "account.fail";

	public static final String ACCONT_SUCCESS = "account.success";

	public static final String HAS_LOGIN = "has.login";

	public static final String NOT_LOGIN = "not.login";

	public static final String LOGOUT_SUCCESS = "logout.success";

}
