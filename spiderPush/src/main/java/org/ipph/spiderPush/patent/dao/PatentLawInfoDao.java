package org.ipph.spiderPush.patent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ipph.spiderPush.patent.entity.PatentLawInfoModel;

@Mapper
public interface PatentLawInfoDao {
	/**
	 * 添加法律状态对象
	 * @param lawInfo
	 * @return
	 */
	public int add(PatentLawInfoModel lawInfo);
	/**
	 * 根据主键获取对象
	 * @param id
	 * @return
	 */
	public PatentLawInfoModel getById(String id);
}
