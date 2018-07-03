package org.ipph.spider.processor;

import javax.annotation.Resource;

import org.ipph.spider.entity.PageLog;
import org.ipph.spider.enumeration.LogModelEnum;
import org.ipph.spider.enumeration.PageLogExceptionEnum;
import org.ipph.spider.enumeration.PageLogStatusEnum;
import org.ipph.spider.exception.SpiderConnectionException;
import org.ipph.spider.exception.SpiderParserException;
import org.ipph.spider.service.IPageLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class AbstractPageProcessor<T> implements PageProcessor{
	
	@Resource
	private IPageLogService pageLogService;
	
	protected Logger logger=LoggerFactory.getLogger(this.getClass());
	
	protected Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	protected Spider spider;
	
	protected LogModelEnum logModel=LogModelEnum.NULL;
	
	protected AbstractPageProcessor(LogModelEnum logModel) {
		this.logModel=logModel;
	}
	
	public void init() {
		if(null==this.spider) {
			synchronized (Spider.class) {
				if(null==this.spider) {
					this.spider=Spider.create(this).thread(10);
				}
			}
		}
	}
	
	@Override
	public void process(Page page) {
		
		if(null==page) {
			return ;
		}
		
		PageLog pageLog=null;
		
		try {
			if(null==page.getRawText()) {
				throw new SpiderConnectionException(page.getRequest().getUrl(),"爬虫爬取地址异常！！！");
			}
			//记录日志
			logger.info("准备处理数据"+page.getRequest().getUrl());
			
			pageLog=pageLogService.insert(page.getRequest().getUrl());
			
			processPage(page);
			
			if(null!=pageLog) {
				pageLog.setStatus(PageLogStatusEnum.SUCCESS);
				pageLog.setNote(PageLogStatusEnum.SUCCESS.getResult());
			}
			
		} catch (SpiderParserException spiderParserException) {
			logger.error("解析数据异常"+spiderParserException.getUrl());
			logger.error(spiderParserException.getMessage());
			
			if(null!=pageLog) {
				pageLog.setStatus(PageLogStatusEnum.FAIL);
				pageLog.setNote(spiderParserException.getMessage());
				pageLog.setExceptionType(spiderParserException.getExceptionType());
			}
			
			
		}catch(SpiderConnectionException spiderConnectionException) {
			logger.error("数据连接异常"+spiderConnectionException.getUrl());
			logger.error(spiderConnectionException.getMessage());
			
			if(null!=pageLog) {
				pageLog.setStatus(PageLogStatusEnum.FAIL);
				pageLog.setNote(spiderConnectionException.getMessage());
				pageLog.setExceptionType(spiderConnectionException.getExceptionType());
			}
		}catch(Exception e) {
			logger.error("保存数据异常"+page.getRequest().getUrl());
			logger.error(e.getMessage());
			
			if(null!=pageLog) {
				pageLog.setStatus(PageLogStatusEnum.FAIL);
				pageLog.setNote(PageLogStatusEnum.FAIL.getResult());
				pageLog.setExceptionType(PageLogExceptionEnum.OPER_EXCEPTION);
				
			}
		}
		
		//日志成功
		logger.info("保存数据成功"+page.getRequest().getUrl());
		
		pageLogService.update(pageLog);
	}
	
	protected abstract void processPage(Page page) throws Exception;
	
	@Override
	public Site getSite() {
		return site;
	}
	
}
