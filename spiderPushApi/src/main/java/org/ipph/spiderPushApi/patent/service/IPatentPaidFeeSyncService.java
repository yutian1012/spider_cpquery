package org.ipph.spiderPushApi.patent.service;

import java.util.List;

public interface IPatentPaidFeeSyncService {
	
	public Long getTotals();
	
	public List<String> getPatentInfoListLimit(int start,int size);
	
	public void syncPatentPaidFee(String url) throws Exception;
	
}
