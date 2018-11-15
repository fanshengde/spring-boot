package com.fsd.web.utils;

public class MyResponseCode {
//	SUCCESS(200, "请求成功"), WARN(500, "请求失败");

	private int code;
	private String msg;
	
	MyResponseCode(int code) {
		this.code = code;
	}
	
	MyResponseCode(int code, String msg) {
		this(code);
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
