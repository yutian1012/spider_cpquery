package org.ipph.spiderPush.patent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.ipph.spiderPush.patent.dao.PatentInfoDao;
import org.ipph.spiderPush.patent.entity.PatentInfo;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatentInfoServiceImpl implements IPatentInfoService{

	@Resource
	private PatentInfoDao patentInfoDao;
	
	@Override
	public int addPatentInfo(PatentInfo patent) {
		if(isExists(patent.getAppNumber())) {
			return 0;
		}
		return patentInfoDao.addPatentInfo(patent);
	}

	@Override
	public PatentInfo getPatentInfo(String appNumber) {
		return patentInfoDao.getPatentInfo(appNumber);
	}

	@Override
	public List<PatentInfo> getPatentInfoListLimit(int start, int size) {
		Map<String, Integer> params=new HashMap<>();
		params.put("start", start);
		params.put("size", size);
		return patentInfoDao.getPatentInfoListLimit(params);
	}

	@Override
	public long getTotals() {
		return patentInfoDao.getTotals();
	}

	@Override
	public int batchAdd(List<PatentInfo> patentList) {
		return patentInfoDao.batchAdd(patentList);
	}

	@Override
	public boolean isExists(String appNumber) {
		return patentInfoDao.isExists(appNumber);
	}

	@Override
	public int syncPatent(String[] appNumbers) {
		
		int result=0;
		
		for(String appNumber:appNumbers) {
			if(!isExists(appNumber)) {
				PatentInfo patent=new PatentInfo();
				patent.setAppNumber(appNumber);
				
				result+=addPatentInfo(patent);
			}
		}
		
		return result;
	}

	@Override
	public List<String> getPatentListLimit(int start, int size) {
		Map<String, Integer> params=new HashMap<>();
		params.put("start", start);
		params.put("size", size);
		return patentInfoDao.getPatentListLimit(params);
	}

}
