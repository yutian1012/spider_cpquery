package org.ipph.spiderPushApi.patent.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.ipph.spiderPushApi.common.Response;
import org.ipph.spiderPushApi.patent.PatentSyncApi;
import org.ipph.spiderPushApi.patent.service.IPatentPaidFeeSyncService;

/**
 * 内部使用线程池的方式实现专利信息同步
 */
public abstract class AbstractThreadPoolPatentPaidFeeSyncServiceImpl implements IPatentPaidFeeSyncService{
	
	private ExecutorService threadPool=Executors.newFixedThreadPool(10);
	
	private PatentSyncApi patentSyncApi=new PatentSyncApi();
	
	@Override
	public void syncPatentPaidFee(String url)throws Exception {
		if(null==url) {
			throw new Exception("未配置爬虫的同步费用地址!!!");
		}
		
		 //获取待同步到专利数量
		 long total=getTotals();
		 
		 long from=0;
		 long size=50;
		 
		 while(from<total) {
			 threadPool.execute(new PatentSyncRunnable(url,from, size));
			 from+=size;
		 }
	}
	
	/**
	  * 定义同步线程
	  * @author dell2
	  *
	  */
	 class PatentSyncRunnable implements Runnable{
		 private String url;
		 private Long from;
		 private Long to;
		 
		 public PatentSyncRunnable(String url,Long from ,Long to) {
			 this.url=url;
			 this.from=from;
			 this.to=to;
		 }

		@Override
		public void run() {
			List<String> patentList=getPatentInfoListLimit(from.intValue(),to.intValue());
			 if(null==patentList||patentList.size()==0) {
				 return ;
			 }
			 processPatentList(url,patentList);
			 
			 patentList.clear();
		}
		 
	 }
	 /**
	  * 处理数据
	  * @param patentList
	  */
	 private void processPatentList(String url,List<String> patentList) {
		 String[] appNumbers=new String[patentList.size()];
		 
		 patentList.toArray(appNumbers);
		 
		 try {
			Response response=patentSyncApi.syncPaidFee(url,appNumbers);
			
			processResponse(response);
			
		} catch (Exception e) {
		}
	 }
	 
	 /**
	  * 处理返回数据
	  * @param response
	  */
	 protected abstract void processResponse(Response response);
}
