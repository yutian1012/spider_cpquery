package org.ipph.spider.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.ipph.spider.entity.PageLogReport;
import org.ipph.spider.mail.MailService;
import org.ipph.spider.service.IPageLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{
	
	@Resource
	private IPageLogService pageLogService;
	@Resource
	private MailService mailService;
	@Value("${mail.toReportMail.addr}")
    private String to;
	@Value("${mail.reportTemplate}")
	private String template;
	
	private Logger logger=LoggerFactory.getLogger(ScheduledTasks.class);

    /**
     * 每天发送一份邮件，反馈当前爬虫运行结果
     * 凌晨1点发送报告
     */
   // @Scheduled(cron = "0 0 1 * * * ")
	 @Scheduled(cron = "0 54 13 * * * ")
    public void reportSpiderResult(){
    	
    	logger.info("preparing report spider result!");
    	
    	/**
    	 * 获取上一天的统计数据
    	 */
    	List<PageLogReport> result=pageLogService.reportSipderResult(DateUtils.addDays(new Date(), -1));
    	
    	//mailService.sendSimpleMail(to, "爬虫邮件报告", getMailContent(result));
    	
    	Map<String,Object> params=new HashMap<>();
    	params.put("reportList", result);
    	
    	mailService.sendHtmlTemplateMail(to, "爬虫邮件报告", template, params);
        
        logger.info("end report spider result!");
    }
}