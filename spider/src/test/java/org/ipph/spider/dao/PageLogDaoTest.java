package org.ipph.spider.dao;


import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void testAdd() throws ParseException {
		String id=UUID.randomUUID().toString();
		PageLog pageLog=new PageLog();
		pageLog.setId(id);
		pageLog.setNote("测试");
		pageLog.setStatus(PageLogStatusEnum.PROCESSING);
		pageLog.setUrl("url");
		//Date date=sdf.parse("2018-07-04");
		pageLog.setCreateDate(new Date());
		
		pageLogDao.add(pageLog);
		
		PageLog tmp=pageLogDao.getById(id);
		assertNotNull(tmp);
	}
	
	

}
