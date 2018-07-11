package org.ipph.spider.patent.service;

import java.util.List;

import org.ipph.spider.patent.entity.PatentInfo;


public interface IPatentInfoService {
	public int addPatentInfo(PatentInfo patent);
	
	public PatentInfo getPatentInfo(String appNumber);
	
	public List<PatentInfo> getPatentInfoListLimit(int start,int size);
	
	public long getTotals();
	
	public int batchAdd(List<PatentInfo> patentList);
	
	public boolean isExists(String appNumber);
	
	public int syncPatent(String[] appNumbers);
	
	public int delByAppNumber(String appNumber);
}
