package org.ipph.spiderPushApi.patent.service.impl;

import java.util.List;

import org.apache.http.conn.HttpHostConnectException;
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
		 Long size=50L;
		 
		 while(from<total) {
			 List<String> list=getPatentInfoListLimit(from.intValue(), size.intValue());
			 if(null!=list&&list.size()>0) {
				 String[] appNumbers=new String[list.size()];
				 list.toArray(appNumbers);
				 try {
					Response response=patentSyncApi.syncPaidFee(url,appNumbers);
					
					processResponse(response);
				} catch (Exception e) {
					if(e instanceof HttpHostConnectException) {
						throw new Exception("服务连接异常，请检查爬虫系统是否启动，或网络配置是否正确!!!");
					}
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
