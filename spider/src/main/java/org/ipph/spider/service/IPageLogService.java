package org.ipph.spider.service;

import java.util.Date;
import java.util.List;

import org.ipph.spider.entity.PageLog;
import org.ipph.spider.entity.PageLogReport;

public interface IPageLogService {

	public PageLog insert(String pageUrl);
	
	public void update(PageLog pageLoge);
	
	public PageLog getById(String id);
	
	public List<PageLogReport> reportSipderResult(Date reportDate);
}
