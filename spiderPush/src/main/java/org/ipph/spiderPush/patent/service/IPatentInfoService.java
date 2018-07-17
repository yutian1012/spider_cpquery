package org.ipph.spiderPush.patent.service;

import java.util.List;

import org.ipph.spiderPush.patent.entity.PatentInfo;


public interface IPatentInfoService {
	public int addPatentInfo(PatentInfo patent);
	
	public PatentInfo getPatentInfo(String appNumber);
	
	public List<PatentInfo> getPatentInfoListLimit(int start,int size);
	
	public long getTotals();
	
	public int batchAdd(List<PatentInfo> patentList);
	
	public boolean isExists(String appNumber);
	
	public int syncPatent(String[] appNumbers);
	
	public List<String> getPatentListLimit(int start,int size);
}
