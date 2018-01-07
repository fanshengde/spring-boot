package com.fsd.site.datasource.mysql.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.site.datasource.mysql.bean.Author;
import com.fsd.site.datasource.mysql.service.AuthorService;
import com.fsd.site.util.JsonUtil;

@RestController
@RequestMapping(value = "/data/jdbc/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> getAuthorList(HttpServletRequest request){
		List<Author> authorList = this.authorService.findAuthorList();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("total", authorList.size());
		param.put("rows", authorList);
		
		return param;
	}
	
	
	@RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.GET)
	public Author getAuthor(@PathVariable Long userId, HttpServletRequest request) {
		Author author = this.authorService.findAuthor(userId);
		if(author == null) {
			throw new RuntimeException("query error for getAuthor");
		}
		return author;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//	public void add(@RequestBody Author author) throws JSONException {
//	public void add(@RequestBody String  requestData) throws JSONException {
	public void add(@RequestBody JSONArray  jsonArray) throws JSONException {
		Map<String, String> param = JsonUtil.getInstance().alibaba4JsonArray(jsonArray);
		
		Author author = new Author();
		
		if(author != null) {
			try {
				author.setId(Long.valueOf(param.get("id")));
			}catch(Exception e) {
				author.setId(Long.valueOf(UUID.randomUUID().toString()));
			}
		}
		author.setRealName(param.get("realName"));
		author.setNickName(param.get("nickName"));
		
		try {
			this.authorService.add(author);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("add error for AuthorController.class");
		}
	}
	
	@RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.PUT)
	public void udpate(@PathVariable Long userId, @RequestBody JSONObject jsonObject) throws JSONException {
		Author author = this.authorService.findAuthor(userId);
		String realName = jsonObject.getString("real_name");
		String nickName = jsonObject.getString("nict_name");
		
		author.setRealName(realName);
		author.setNickName(nickName);
		
		try {
			this.authorService.update(author);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("udpate error AuthorController.class");
		}
	}
	
	@RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long userId) {
		try {
			this.authorService.delete(userId);
		}catch(Exception e) {
			throw new RuntimeException("delete error AuthorController.class");
		}
	}
	
}
