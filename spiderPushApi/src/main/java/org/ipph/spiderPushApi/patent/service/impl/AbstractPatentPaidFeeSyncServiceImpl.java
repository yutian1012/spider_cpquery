package org.ipph.spiderPushApi.patent.service.impl;

import java.util.List;

import org.ipph.spiderPushApi.common.Response;
import org.ipph.spiderPushApi.patent.PatentSyncApi;
import org.ipph.spiderPushApi.patent.service.IPatentPaidFeeSyncService;

public abstract class AbstractPatentPaidFeeSyncServiceImpl implements IPatentPaidFeeSyncService{
	
	private PatentSyncApi patentSyncApi=new PatentSyncApi();
	
	@Override
	public void syncPatentPaidFee(String url) throws Exception{
		if(null==url) {
			throw new Exception("未配置爬虫的同步费用地址!!!");
		}
		
		 //获取待同步到专利数量
		 Long total=getTotals();
		 
		 Long from=0L;
		 Long size=1000L;
		 
		 while(from<total) {
			 List<String> list=getPatentInfoListLimit(from.intValue(), size.intValue());
			 if(null!=list&&list.size()>0) {
				 String[] appNumbers=new String[list.size()];
				 list.toArray(appNumbers);
				 try {
					Response response=patentSyncApi.syncPaidFee(url,appNumbers);
					
					processResponse(response);
				} catch (Exception e) {
				}
				
				list.clear();
			 }
			 from+=size;
		 }
	}
	
	/**
	  * 处理返回数据
	  * @param response
	  */
	 protected abstract void processResponse(Response response);
}
