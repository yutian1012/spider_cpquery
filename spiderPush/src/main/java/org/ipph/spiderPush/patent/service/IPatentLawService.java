package org.ipph.spiderPush.patent.service;

import org.ipph.spiderPush.patent.entity.PatentLawModel;

public interface IPatentLawService {
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
