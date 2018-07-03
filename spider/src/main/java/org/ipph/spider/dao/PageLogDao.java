package org.ipph.spider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ipph.spider.entity.PageLog;

@Mapper
public interface PageLogDao {
	/**
	 * 添加日志
	 * @param pageLog
	 * @return
	 */
	public int add(PageLog pageLog);
	/**
	 * 更新日志
	 * @param pageLog
	 * @return
	 */
	public int update(PageLog pageLog);
	
	public PageLog getById(String id);
}
