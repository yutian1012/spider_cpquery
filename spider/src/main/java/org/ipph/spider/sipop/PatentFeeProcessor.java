package org.ipph.spider.sipop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

public class PatentFeeProcessor implements PageProcessor{
	
	private static Logger logger=LoggerFactory.getLogger(GithubRepoPageProcessor.class);

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		Json data=page.getJson();
		logger.info(data.get());
	}
	
	public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.sipop.cn/patent-interface-search/patentDetail/queryCostInfo?p_application_docNum=CN201510830923.4").thread(5).run();
    }

}
