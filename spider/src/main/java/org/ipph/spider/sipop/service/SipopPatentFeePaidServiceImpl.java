package org.ipph.spider.sipop.service;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spider.sipop.dao.SipopPatentFeePaidDao;
import org.ipph.spider.sipop.entity.SipopPatentFeePaid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SipopPatentFeePaidServiceImpl implements ISipopPatentFeePaidService{
	
	@Resource
	private SipopPatentFeePaidDao dao;

	@Override
	public int batchAdd(List<SipopPatentFeePaid> paidList, String appNumber) {
		if(null==paidList||paidList.size()==0) {
			return 0;
		}

		for(SipopPatentFeePaid sipopPatentFeePaid:paidList) {
			sipopPatentFeePaid.setAppNumber(appNumber);
		}
		
		return dao.batchAdd(paidList);
	}

	@Override
	public int add(SipopPatentFeePaid paidFee, String appNumber) {
		if(null==paidFee) {
			return 0;
		}
		paidFee.setAppNumber(appNumber);
		return dao.add(paidFee);
	}

	@Override
	public List<SipopPatentFeePaid> getByAppNumber(String appNumber) {
		return dao.getByAppNumber(appNumber);
	}

	@Override
	public int delByAppNumber(String appNumber) {
		return dao.delByAppNumber(appNumber);
	}
}
