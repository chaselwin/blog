package com.chasel.blog.constant;

/**
 * 返回结果
 * 
 * @author chasel
 *
 */
public class ResponseResult {
	// 成功状态
	private ResponseStatus status;

	// 详细描述
	private String desc;

	// 返回信息体
	private Object data;

	public ResponseResult() {
	}

	public ResponseResult(ResponseStatus status, String desc) {
		super();
		this.status = status;
		this.desc = desc;
	}

	public ResponseResult(ResponseStatus status, String desc, Object data) {
		super();
		this.status = status;
		this.desc = desc;
		this.data = data;
	}

	public int getStatus() {
		return status.getCode();
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
