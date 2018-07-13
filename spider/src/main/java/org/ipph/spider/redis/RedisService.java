package org.ipph.spider.redis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	public static final String PATENT_FEE_PAID_KEY="appNumber_num";//申请号_条数
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 判断redis中是否已经初始化过数据
	 * @return
	 */
	public boolean isAlreadyInit() {
		return stringRedisTemplate.hasKey(RedisService.PATENT_FEE_PAID_KEY);
	}

	/**
	 * 判断是否存在
	 * @param value
	 * @return
	 */
    public boolean isExists(String key) {
    	return stringRedisTemplate.boundHashOps(RedisService.PATENT_FEE_PAID_KEY).hasKey(key);
    }
    /**
     * 向set集合中添加value
     * @param value
     */
    public void add(String key,String value) {
    	stringRedisTemplate.boundHashOps(RedisService.PATENT_FEE_PAID_KEY).put(key, value);
    }
    /**
     * 添加集合操作
     * @param data
     */
    public void addAll(Map<String,String> data) {
    	stringRedisTemplate.boundHashOps(RedisService.PATENT_FEE_PAID_KEY).putAll(data);
    }
    /**
     * 获取set集合对象
     * @return
     */
    public Map<Object, Object> getAll(){
    	return stringRedisTemplate.boundHashOps(RedisService.PATENT_FEE_PAID_KEY).entries();
    }
    /**
     * 获取数据量
     * @param key
     * @return
     */
    public int getNum(String key) {
    	String num=(String)stringRedisTemplate.boundHashOps(RedisService.PATENT_FEE_PAID_KEY).get(key);
    	if(null==num||"".equals(num)) {
    		return 0;
    	}
    	return Integer.parseInt(num);
    }
}
