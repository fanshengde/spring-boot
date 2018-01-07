package com.fsd.service.ehcache;


import java.sql.Timestamp;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("ehcache.cacheService")
public class CacheService {
	
	@Cacheable(value="ehcache.cacheService")
	public long getByCache() {
		try {
			
			Thread.sleep(3 * 1000);
			
		}catch(InterruptedException  e) {
			e.printStackTrace();
		}
		
		return new Timestamp(System.currentTimeMillis()).getTime();
	}
	
	@CachePut(value = "ehcache")
	public long save() {
		long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
		System.out.println("进行缓存 ：" + timestamp);
		return timestamp;
	}
	
	@CacheEvict(value="ehcache")
	public void delete() {
		System.out.println("删除缓存");
	}
}
