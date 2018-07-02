package org.ipph.spider.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spider.entity.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptServiceImplTest {
	
	@Resource
	private DeptService deptService;
	
	@Test
	public void testList() {
		
		List<Dept> list=deptService.list();
		assertNotNull(list);
		assertTrue(list.size()>0);
	}

}
