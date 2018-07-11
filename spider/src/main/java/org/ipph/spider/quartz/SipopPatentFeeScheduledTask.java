package org.ipph.spider.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spider.jms.JmsConfiguration;
import org.ipph.spider.jms.JmsSender;
import org.ipph.spider.patent.entity.PatentInfo;
import org.ipph.spider.patent.service.IPatentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SipopPatentFeeScheduledTask {

	private Logger logger=LoggerFactory.getLogger(SipopPatentFeeScheduledTask.class);
	
	@Resource
	private IPatentInfoService patentInfoService;
	
	@Resource
	private JmsSender jmsSender;
	
	 @Scheduled(cron = "0 46 15 * * * ")
	 public void executeSpiderSipop(){
		//获取待同步到专利数量
		 long total=patentInfoService.getTotals();
		 
		 Long from=0L;
		 Long to=0L;
		 long size=1000;
		 
		 while(to<total) {
			 to+=size;
			 logger.info("get patent info from db ... start "+from+" end "+to);
			 List<PatentInfo> patentList=patentInfoService.getPatentInfoListLimit(from.intValue(),to.intValue());
			 if(null==patentList||patentList.size()==0) {
				 return ;
			 }
			 processPatentList(patentList);
			 
			 patentList.clear();
			 from+=size;
		 }
	 }
	 
	 /**
	  * 处理数据
	  * @param patentList
	  */
	 public void processPatentList(List<PatentInfo> patentList) {
		 
		 for(int i=0;i<patentList.size();i++) {
			 String appNumber=patentList.get(i).getAppNumber();
			 logger.info("send message to spider appnumber:"+appNumber);
			 jmsSender.sendByQueue(appNumber,JmsConfiguration.PATENT_FEE_QUEUE_NAME );
		 }
	 }
}
