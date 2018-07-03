package org.ipph.spider.service;

import org.ipph.spider.entity.PageLog;

public interface IPageLogService {

	public PageLog insert(String pageUrl);
	
	public void update(PageLog pageLoge);
	
	public PageLog getById(String id);
}
