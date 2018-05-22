package com.chasel.blog.exception;

/**
 * 自定义异常
 * 
 * @author chasel
 *
 */
public class BlogException extends Exception {

	private static final long serialVersionUID = 5607034216119537665L;

	// 错误代码code
	private String errCode;

	// 错误信息Msg
	private Object errMsg;

	public BlogException() {
	}

	public BlogException(Object errMsg) {
		super();
		this.errMsg = errMsg;
	}

	public BlogException(String errCode, Object errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public Object getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(Object errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "BlogException [errCode=" + errCode + ", errMsg=" + errMsg + "]";
	}

}
