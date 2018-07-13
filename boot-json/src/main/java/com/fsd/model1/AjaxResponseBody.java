package com.fsd.model1;

import java.util.List;

import com.fsd.entity1.User;

public class AjaxResponseBody {
	private String msg;
	private List<User> result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<User> getResult() {
		return result;
	}

	public void setResult(List<User> result) {
		this.result = result;
	}

}
