package org.ipph.spider.patent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.ipph.spider.patent.entity.PatentInfo;

@Mapper
public interface PatentInfoDao {
	
	public int addPatentInfo(PatentInfo patent);
	
	public PatentInfo getPatentInfo(String appNumber);
	
	public List<PatentInfo> getPatentInfoListLimit(Map<String,Integer> params);
	
	public long getTotals();
	
	public int batchAdd(List<PatentInfo> patentList);
	
	public boolean isExists(String appNumber);
	
	public int delByAppNumber(String appNumber);
}
