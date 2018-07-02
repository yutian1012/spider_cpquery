package org.ipph.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.ipph.spider.entity.Dept;

//mybatis会自动扫描到该注解
@Mapper
public interface DeptDao {
	public boolean addDept(Dept dept);
	
	public Dept findById(Long id);
	
	public List<Dept> findAll();
}
