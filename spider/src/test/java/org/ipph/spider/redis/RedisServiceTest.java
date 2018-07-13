package org.ipph.spider.redis;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

	@Resource
	private RedisService redisService;
	
	@Test
	public void testRedisSet() {
		
		Map<String,String> data=new HashMap<>();
		data.put("name1","1");
		data.put("name2","2");
		redisService.addAll(data);
		
		//从redis中获取
		boolean exists=redisService.isExists("name1");
		assertTrue(exists);
		
		redisService.add("name3","3");
		
		Map<Object,Object> tmp=redisService.getAll();
		assertTrue(tmp.size()==3);
		
		assertTrue(redisService.getNum("name3")==3);
		
	}
	
	@Test
	public void testRedisKey() {
		assertTrue(!redisService.isAlreadyInit());
		
		redisService.add("name1","1");
		
		assertTrue(redisService.isAlreadyInit());
	}

}
