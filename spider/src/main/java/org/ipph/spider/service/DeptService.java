package org.ipph.spider.service;

import java.util.List;

import org.ipph.spider.entity.Dept;

public interface DeptService {
	public boolean add(Dept dept);
	
	public Dept get(Long id);
	
	public List<Dept> list();
}
