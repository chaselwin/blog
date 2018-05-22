package com.chasel.blog.constant;
/**
 * 登录类型枚举类
 * 
 * @author chasel
 *
 */
public enum EIdentityType {
	//账号
	ACCOUNT(0,"account"),
	//手机号
	PHONE(1,"phone"),
	//邮箱
	EMAIL(2,"email");
	
	private int index;
	
	private String String;

	private EIdentityType(int index, java.lang.String string) {
		this.index = index;
		String = string;
	}

	public int getIndex() {
		return index;
	}

	public String getString() {
		return String;
	}
	
}
