package org.ipph.spider.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spider.jms.JmsConfiguration;
import org.ipph.spider.jms.JmsSender;
import org.ipph.spider.patent.entity.PatentInfo;
import org.ipph.spider.patent.service.IPatentInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SipopPatentFeeScheduledTaskTest {
	
	private Logger logger=LoggerFactory.getLogger(SipopPatentFeeScheduledTaskTest.class);
	
	@Resource
	private JmsSender jmsSender;

	@Resource
	private IPatentInfoService patentInfoService;
	
	@Test
	public void testSpiderPatentFee() throws InterruptedException {
		
		 List<PatentInfo> patentList=patentInfoService.getPatentInfoListLimit(0,10);
		 
		 for(int i=0;i<patentList.size();i++) {
			 String appNumber=patentList.get(i).getAppNumber();
			 logger.info("send message to spider appnumber:"+appNumber);
			 jmsSender.sendByQueue(appNumber,JmsConfiguration.PATENT_FEE_QUEUE_NAME );
		 }
		 
		 Thread.currentThread().join();
	}

}
