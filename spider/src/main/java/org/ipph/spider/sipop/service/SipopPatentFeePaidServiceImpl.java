package org.ipph.spider.sipop.service;

import java.util.Iterator;
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

	/**
	 * 这里不考虑并发问题，并发问题通过数据库过滤来实现
	 * 只要保证数据库申请号没有重复，爬虫获取数据后再通过消息传递过来就不会存在重复问题
	 * 保证数据库唯一可通过数据库唯一键来实现
	 */
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
		
		//加载数据库中的数据
		List<SipopPatentFeePaid> list=getByAppNumber(appNumber);
		if(null!=list&&list.size()>0) {
			if(list.size()==paidList.size()) {
				return 0;
			}
			//挑出待插入的数据
			for(SipopPatentFeePaid paidFee:list) {
				for(Iterator<SipopPatentFeePaid> iter=paidList.iterator();iter.hasNext();)
				if(paidFee.getHash().equals(iter.next().getHash())) {
					iter.remove();
				}
			}
		}
		
		dao.batchAdd(paidList);//重复性问题如何处理
		
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
		
		List<SipopPatentFeePaid> list= dao.getByAppNumberIn(appNumbers);
		if(null==list||list.size()==0) {//未获取爬虫数据如何处理？？，这里暂时不做任何处理
			
		}
		
		return list;
	}
}
