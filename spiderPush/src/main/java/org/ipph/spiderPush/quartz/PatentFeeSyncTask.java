package org.ipph.spiderPush.quartz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 专利费用同步定时器
 */

@Component
@EnableScheduling
public class PatentFeeSyncTask {
	
	private Logger logger=LoggerFactory.getLogger(PatentFeeSyncTask.class);
	
	@Value("${spider.patentfee.sync.url}")
	private String url;

	@Resource
	private PatentFeeSyncService patentFeeSyncService;

	@Scheduled(cron = "0 31 11 * * * ")
	public void syncPatent(){
		 
		 logger.info("sync patent fee ...");
		 
		 try {
			 patentFeeSyncService.syncPatentPaidFee(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
}
