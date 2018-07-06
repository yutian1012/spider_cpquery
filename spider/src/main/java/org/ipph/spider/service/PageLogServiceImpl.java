package org.ipph.spider.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.ipph.spider.dao.PageLogDao;
import org.ipph.spider.entity.PageLog;
import org.ipph.spider.entity.PageLogReport;
import org.ipph.spider.enumeration.PageLogStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PageLogServiceImpl implements IPageLogService{
	
	@Resource
	private PageLogDao pageLogDao;
	/**
	 * 插入数据
	 * @param pageUrl
	 * @return
	 */
	@Override
	public PageLog insert(String pageUrl) {
		PageLog pageLog=new PageLog();
		
		pageLog.setId(UUID.randomUUID().toString());
		pageLog.setNote("数据处理中...");
		pageLog.setUrl(pageUrl);
		pageLog.setStatus(PageLogStatusEnum.PROCESSING);
		pageLog.setCreateDate(new Date());
		
		pageLogDao.add(pageLog);
		
		return pageLog;
	}
	
	@Override
	public void update(PageLog pageLog) {
		pageLogDao.update(pageLog);
	}

	@Override
	public PageLog getById(String id) {
		return pageLogDao.getById(id);
	}

	@Override
	public List<PageLogReport> reportSipderResult(Date reportDate) {
		return pageLogDao.reportSipderResult(reportDate);
	}
	

}
