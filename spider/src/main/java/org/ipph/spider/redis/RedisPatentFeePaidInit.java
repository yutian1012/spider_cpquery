package org.ipph.spider.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.ipph.spider.sipop.dao.SipopPatentFeePaidDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 初始化数据到redis中
 */
@Component
public class RedisPatentFeePaidInit {
	
	private Logger logger=LoggerFactory.getLogger(RedisPatentFeePaidInit.class);
	
	@Resource
	private SipopPatentFeePaidDao sipopPatentFeePaidDao;
	@Resource
	private RedisService redisService;

	@PostConstruct
	public void initData() {
		
		//判断redis中是否已经有数据
		if(redisService.isAlreadyInit()) {
			return;
		}
		
		logger.info("init data to redis ....");
		
		//每次加载5万条记录
		long size=50000;
		long total=sipopPatentFeePaidDao.countDistinctAppNumber();
		long from=0;
		Map<String,Long> limit=new HashMap<String,Long>();
		
		while(from<=total) {
			limit.put("from", from);
			limit.put("size", size);
			initDataToRedis(sipopPatentFeePaidDao.getRedisInitData(limit));
			from+=size;
		}
	}
	/**
	 * 初始化数据到redis中
	 * @param patentFeePaidMapList
	 */
	private void initDataToRedis(List<Map<String, Object>> patentFeePaidMapList) {
		if(null==patentFeePaidMapList||patentFeePaidMapList.size()==0) {
			return;
		}
		
		for(Map<String,Object> data:patentFeePaidMapList) {
			redisService.add(data.get("appNumber")+"",data.get("num")+"");
		}
		patentFeePaidMapList.clear();
	}
}
