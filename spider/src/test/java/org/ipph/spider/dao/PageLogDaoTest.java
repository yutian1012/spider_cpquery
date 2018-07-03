package org.ipph.spider.dao;


import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.ipph.spider.entity.PageLog;
import org.ipph.spider.enumeration.PageLogStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageLogDaoTest {

	@Resource
	private PageLogDao pageLogDao;
	
	@Test
	public void testAdd() {
		String id="01c716e0-d44f-4d89-8245-5104ea077f4c";
		PageLog pageLog=new PageLog();
		pageLog.setId(id);
		pageLog.setNote("测试");
		pageLog.setStatus(PageLogStatusEnum.PROCESSING);
		pageLog.setUrl("url");
		pageLogDao.add(pageLog);
		
		PageLog tmp=pageLogDao.getById(id);
		assertNotNull(tmp);
	}
	
	

}
