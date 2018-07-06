package org.ipph.spider.sipop.jms;

import javax.annotation.Resource;

import org.ipph.spider.jms.JmsConfiguration;
import org.ipph.spider.sipop.PatentFeeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SipopPatentFeeJmsReceiver {
	
	private static final Logger logger=LoggerFactory.getLogger(SipopPatentFeeJmsReceiver.class);
	
	private static final String url="http://www.sipop.cn/patent-interface-search/patentDetail/queryCostInfo?p_application_docNum=";
	
	@Resource
	private PatentFeeProcessor patentFeeProcessor;
	
    //指定监听的队列
    @JmsListener(destination = JmsConfiguration.PATENT_FEE_QUEUE_NAME)
    public void receiveByQueue(String appNumber) {
    	logger.info("接收队列消息:" + appNumber);
        //向爬虫中添加url
        patentFeeProcessor.addNewUrl(url+appNumber);
    }
}
