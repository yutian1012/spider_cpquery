package org.ipph.spider.sipop;

import javax.annotation.Resource;

import org.ipph.spider.sipop.entity.SipopPatentFeePaid;
import org.ipph.spider.sipop.parser.SipopPatentFeePaidParser;
import org.ipph.spider.sipop.service.ISipopPatentFeePaidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class PatentFeeProcessor implements PageProcessor{
	
	@Resource
	private SipopPatentFeePaidParser sipopPatentFeePaidParser;
	@Resource
	private ISipopPatentFeePaidService sipopPatentFeePaidService;
	
	private static Logger logger=LoggerFactory.getLogger(PatentFeeProcessor.class);

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		/*Json data=page.getJson();
		logger.info(data.get());*/
		System.out.println(sipopPatentFeePaidParser);
		SipopPatentFeePaid model=(SipopPatentFeePaid) sipopPatentFeePaidParser.parse(page);
		//page.get
		if(null!=model) {
			sipopPatentFeePaidService.add(model, "CN201510830923.4");
		}
	}
	
	public void run(String url) {
		//"http://www.sipop.cn/patent-interface-search/patentDetail/queryCostInfo?p_application_docNum=CN201510830923.4"
		
        Spider.create(this).addUrl(url).thread(5).run();
    }

}
