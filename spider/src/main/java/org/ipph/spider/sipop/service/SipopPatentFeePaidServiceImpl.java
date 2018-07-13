package org.ipph.spider.sipop.service;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spider.redis.RedisService;
import org.ipph.spider.sipop.dao.SipopPatentFeePaidDao;
import org.ipph.spider.sipop.entity.SipopPatentFeePaid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@Transactional
public class SipopPatentFeePaidServiceImpl implements ISipopPatentFeePaidService{
	
	@Resource
	private SipopPatentFeePaidDao dao;
	@Resource
	private RedisService redisService;

	@Override
	public int batchAdd(List<SipopPatentFeePaid> paidList, String appNumber) {
		
		if(null==paidList||paidList.size()==0) {
			return 0;
		}
		
		//判断数据库中是否已经存在
		if(redisService.isExists(appNumber)&&paidList.size()==redisService.getNum(appNumber)) {
			return 0;
		}

		for(SipopPatentFeePaid sipopPatentFeePaid:paidList) {
			sipopPatentFeePaid.setAppNumber(appNumber);
			sipopPatentFeePaid.setHash(DigestUtils.md5DigestAsHex(sipopPatentFeePaid.toString().getBytes()));
			//add(sipopPatentFeePaid, appNumber);
		}
		
		dao.batchAdd(paidList);
		
		//添加到缓存数据中
		redisService.add(appNumber, paidList.size()+"");
		
		return paidList.size();
	}

	@Override
	public int add(SipopPatentFeePaid paidFee, String appNumber) {
		if(null==paidFee) {
			return 0;
		}
		paidFee.setAppNumber(appNumber);
		
		//判断是否已经存在
		String hash=DigestUtils.md5DigestAsHex(paidFee.toString().getBytes());
		if(!isExist(hash)) {
			paidFee.setHash(hash);
			return dao.add(paidFee);
		}
		
		return 0;
		
	}

	@Override
	public List<SipopPatentFeePaid> getByAppNumber(String appNumber) {
		return dao.getByAppNumber(appNumber);
	}

	@Override
	public int delByAppNumber(String appNumber) {
		return dao.delByAppNumber(appNumber);
	}
	@Override
	public boolean isExist(String hash) {
		return dao.isExist(hash);
	}

	@Override
	public List<SipopPatentFeePaid> getByAppNumberIn(String[] appNumbers) {
		return dao.getByAppNumberIn(appNumbers);
	}
}
