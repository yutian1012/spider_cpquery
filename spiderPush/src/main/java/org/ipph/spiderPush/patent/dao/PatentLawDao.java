package org.ipph.spiderPush.patent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ipph.spiderPush.patent.entity.PatentLawModel;

@Mapper
public interface PatentLawDao {
	/**
	 * 添加法律状态
	 * @param law
	 * @return
	 */
	public int add(PatentLawModel law);
	/**
	 * 获取法律状态
	 * @param id
	 * @return
	 */
	public PatentLawModel getById(String id);
}
