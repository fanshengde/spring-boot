package com.fsd.web.utils;

public class MyResponseEntity {
	private int code;
	private String msg;
	private Object data;

	public MyResponseEntity() {
	}

	public MyResponseEntity(MyResponseCode responseCode) {
		this.code = responseCode.getCode();
		this.msg = responseCode.getMsg();
	}

	public MyResponseEntity(MyResponseCode responseCode, Object data) {
		this(responseCode);
		this.data = data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
