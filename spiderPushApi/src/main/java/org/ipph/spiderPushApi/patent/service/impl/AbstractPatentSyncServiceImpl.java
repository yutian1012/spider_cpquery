package org.ipph.spiderPushApi.patent.service.impl;

import java.util.List;

import org.ipph.spiderPushApi.patent.PatentSyncApi;
import org.ipph.spiderPushApi.patent.service.IPatentSyncService;

public abstract class AbstractPatentSyncServiceImpl implements IPatentSyncService{
	
	private PatentSyncApi patentSyncApi=new PatentSyncApi();
	
	@Override
	public void syncPatent(String url) throws Exception {
		
		if(null==url) {
			throw new Exception("未配置爬虫的同步专利地址!!!");
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
					patentSyncApi.syncPatent(url,appNumbers);
				} catch (Exception e) {
				}
				
				list.clear();
			 }
			 from+=size;
		 }
	}
}
