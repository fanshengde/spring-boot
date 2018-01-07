package com.fsd.site.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;


public class JsonUtil {
	private static JsonUtil jsonUtil = null;

	public static JsonUtil getInstance() {
		if (jsonUtil == null) {
			jsonUtil = new JsonUtil();
		}
		return jsonUtil;
	}

	public Map<String, String> alibaba4JsonArray(JSONArray jsonArray) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		Iterator<?> iterator = jsonArray.iterator();
		
		while(iterator.hasNext()) {
			
			@SuppressWarnings("unchecked")
			Map<String, String> obj = (Map<String, String>) iterator.next();
			
			result.put(obj.get("name"), obj.get("value"));
		}
		
		return result;
	}
	
	
}