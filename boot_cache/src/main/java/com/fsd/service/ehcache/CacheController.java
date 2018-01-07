package com.fsd.service.ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("ehcache.cacheController")
@RequestMapping(value = "/ehcache/cache")
public class CacheController {
	@Autowired
	private CacheService cacheService;

	//query data
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getByCache() {
		Long startTime = System.currentTimeMillis();
		long timestamp = this.cacheService.getByCache();
		Long endTime = System.currentTimeMillis();
		System.out.println("耗时： " + (endTime - startTime));
		return timestamp + "";
	}
	
	
	//save data
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void save() {
		this.cacheService.save();
	}
	
	//delete data
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public void delete() {
		this.cacheService.delete();
	}
	
}
