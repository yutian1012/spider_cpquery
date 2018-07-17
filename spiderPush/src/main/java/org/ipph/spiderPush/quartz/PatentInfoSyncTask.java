package org.ipph.spiderPush.quartz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PatentInfoSyncTask {

	private Logger logger=LoggerFactory.getLogger(PatentInfoSyncTask.class);
	
	@Value("${spider.patent.sync.url}")
	private String url;

	@Resource
	private PatentSyncService patentSyncService;

	@Scheduled(cron = "0 15 15 * * * ")
	public void syncPatent(){
		 
		 logger.info("sync patent ...");
		 
		 try {
			patentSyncService.syncPatent(url);
		} catch (Exception e) {
		}
	 }
	
}
