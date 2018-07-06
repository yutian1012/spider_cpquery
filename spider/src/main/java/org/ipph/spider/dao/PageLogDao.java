package org.ipph.spider.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.ipph.spider.entity.PageLog;
import org.ipph.spider.entity.PageLogReport;

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
	/**
	 * 根据ID获取对象
	 * @param id
	 * @return
	 */
	public PageLog getById(String id);
	/**
	 * 获取每日的统计报表
	 * @return
	 */
	public List<PageLogReport> reportSipderResult(Date reportDate);
}
