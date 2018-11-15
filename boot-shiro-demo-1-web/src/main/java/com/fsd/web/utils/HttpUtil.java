package com.fsd.web.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpUtil {
	private static HttpUtil instance;
	private HttpUtil() {
		
	}
	public static HttpUtil getInstance() {
		if(instance == null) {
			synchronized (HttpUtil.class) {
				if(instance == null) {
					instance = new HttpUtil();
				}
			}
		}
		return instance;
	}
	
	private HttpStatus getStatus(Object result) {
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return status;
	}
	
	public ResponseEntity<?> getResponse(Object result){
		if(result == null){
			MyResponseCode error = new MyResponseCode(4, "not found");
	        return new ResponseEntity<MyResponseCode>(error, getStatus(result));
	    }
	    return new ResponseEntity<Object>(result,getStatus(result));
	}
}
