package org.ipph.spiderPushApi.patent.service;

import java.util.List;

public interface IPatentSyncService {
	
	public Long getTotals();
	
	public List<String> getPatentInfoListLimit(int start,int size);
	
	public void syncPatent(String url)throws Exception;
	
}
