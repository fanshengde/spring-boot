package com.fsd.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
	private static JsonUtil instance;
	private JsonUtil() {
		
	}
	public static JsonUtil getInstance() {
		if(instance == null) {
			synchronized (JsonUtil.class) {
				if(instance == null) {
					instance = new JsonUtil();
				}
			}
		}
		return instance;
	}
	
	
	public String parseList(Object obj) {
		String result = JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
		return result;
	}
}
