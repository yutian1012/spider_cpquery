package org.ipph.spiderPush.patent.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.ipph.spiderPush.patent.dao.PatentLawDao;
import org.ipph.spiderPush.patent.dao.PatentLawInfoDao;
import org.ipph.spiderPush.patent.entity.PatentLawInfoModel;
import org.ipph.spiderPush.patent.entity.PatentLawModel;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatentLawServiceImpl implements IPatentLawService {
	
	@Resource
	private PatentLawDao patentLawDao;
	
	@Resource
	private PatentLawInfoDao patentLawInfoDao;

	@Override
	public int add(PatentLawModel law) {
		
		PatentLawInfoModel lawInfo=law.getLawInfo();
		if(null!=lawInfo) {
			patentLawInfoDao.add(lawInfo);
		}
		
		return patentLawDao.add(law);
	}

	@Override
	public PatentLawModel getById(String id) {
		return patentLawDao.getById(id);
	}
	
}
