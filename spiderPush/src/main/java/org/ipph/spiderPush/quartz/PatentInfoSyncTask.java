package org.ipph.spiderPush.quartz;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.ipph.spiderPush.patent.entity.PatentInfo;
import org.ipph.spiderPush.patent.service.IPatentInfoService;
import org.ipph.spiderPushApi.patent.PatentSyncApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class PatentInfoSyncTask {

	private Logger logger=LoggerFactory.getLogger(PatentInfoSyncTask.class);
	
	private ExecutorService threadPool=Executors.newFixedThreadPool(10);
	
	@Resource
	private IPatentInfoService patentInfoService;
	
	private PatentSyncApi patentSyncApi=new PatentSyncApi();
	
	@Scheduled(cron = "0 55 9 * * * ")
	public void syncPatent(){
		 
		 logger.info("sync patent ...");
		 
		 //获取待同步到专利数量
		 long total=patentInfoService.getTotals();
		 
		 long from=0;
		 long size=1000;
		 
		 while(from<total) {
			 threadPool.execute(new PatentSyncRunnable(from, size));
			 from+=size;
		 }
	 }
	 /**
	  * 定义同步线程
	  * @author dell2
	  *
	  */
	 class PatentSyncRunnable implements Runnable{
		 
		 private Long from;
		 private Long to;
		 
		 public PatentSyncRunnable(Long from ,Long to) {
			 this.from=from;
			 this.to=to;
		 }

		@Override
		public void run() {
			List<PatentInfo> patentList=patentInfoService.getPatentInfoListLimit(from.intValue(),to.intValue());
			 if(null==patentList||patentList.size()==0) {
				 return ;
			 }
			 processPatentList(patentList);
			 
			 patentList.clear();
		}
		 
	 }
	 /**
	  * 处理数据
	  * @param patentList
	  */
	 private void processPatentList(List<PatentInfo> patentList) {
		 String[] appNumbers=new String[patentList.size()];
		 
		 for(int i=0;i<patentList.size();i++) {
			 appNumbers[i]=patentList.get(i).getAppNumber();
		 }
		 
		 try {
			patentSyncApi.syncPatent(appNumbers);
		} catch (Exception e) {
		}
	 }
}
