package com.chasel.blog.constant;

/**
 * 返回状态
 * 
 * @author chasel
 *
 */
public enum ResponseStatus {

	// 成功状态
	SUCCESS(200),

	// 失败状态
	FAIL(-1),
	
	//非登录转态
	NOT_LOGIN(403);

	private int code;

	public int getCode() {
		return code;
	}

	private ResponseStatus(int code) {
		this.code = code;
	}

}
