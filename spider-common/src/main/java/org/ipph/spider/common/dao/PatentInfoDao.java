package org.ipph.spider.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.ipph.spider.common.entity.PatentInfo;

@Mapper
public interface PatentInfoDao {
	
	public int addPatentInfo(PatentInfo patent);
	
	public PatentInfo getPatentInfo(String appNumber);
	
	public List<PatentInfo> getPatentInfoListLimit(Map<String,Integer> params);
	
	public long getTotals();
	
	public int batchAdd(List<PatentInfo> patentList);
	
	public boolean isExists(String appNumber);
}
