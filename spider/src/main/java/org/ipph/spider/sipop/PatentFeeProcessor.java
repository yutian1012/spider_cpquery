package org.ipph.spider.sipop;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.ipph.spider.aop.PageProcessorLog;
import org.ipph.spider.enumeration.LogModelEnum;
import org.ipph.spider.exception.SpiderConnectionException;
import org.ipph.spider.processor.AbstractPageProcessor;
import org.ipph.spider.sipop.entity.SipopPatentFeePaid;
import org.ipph.spider.sipop.parser.SipopPatentFeePaidParser;
import org.ipph.spider.sipop.service.ISipopPatentFeePaidService;
import org.ipph.spider.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;

@Component
public class PatentFeeProcessor extends AbstractPageProcessor<SipopPatentFeePaid>{
	
	@Autowired
	protected PatentFeeProcessor() {
		super(LogModelEnum.PATENTFEE);
		this.init();
		this.spider.setExitWhenComplete(false).runAsync();
	}

	@Resource
	private SipopPatentFeePaidParser sipopPatentFeePaidParser;
	@Resource
	private ISipopPatentFeePaidService sipopPatentFeePaidService;
	
	/*@Override
	//拦截器无法正确拦截，可能与线程池有关
	@PageProcessorLog(model=LogModelEnum.PATENTFEE)
	public void process(Page page) {
		List<SipopPatentFeePaid> list=null;
		//获取查询参数申请号
		String appNumber=HttpUtil.getRequestParam(page.getRequest().getUrl(), "p_application_docNum");
		
		if(null!=appNumber) {
			try {
				list = sipopPatentFeePaidParser.parseList(page);
				if(null!=list&&list.size()>0) {
					sipopPatentFeePaidService.batchAdd(list, appNumber);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	/**
	 * 向线程中添加新的url地址
	 * @param url
	 */
	public void addNewUrl(String ... url) {
		this.spider.addUrl(url);
	}

	@Override
	protected void processPage(Page page) throws Exception{

		if(null==page.getRawText()) {
			throw new SpiderConnectionException(page.getRequest().getUrl(),"爬虫爬取地址异常！！！");
		}
		
		List<SipopPatentFeePaid> list=null;
		//获取查询参数申请号
		String appNumber=HttpUtil.getRequestParam(page.getRequest().getUrl(), "p_application_docNum");
		
		if(null!=appNumber) {
			list = sipopPatentFeePaidParser.parseList(page);
			if(null!=list&&list.size()>0) {
				sipopPatentFeePaidService.batchAdd(list, appNumber);
			}
		}
		
	}
	
}
