package org.ipph.spider.service;


import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.ipph.spider.entity.PageLog;
import org.ipph.spider.entity.PageLogReport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageLogServiceImplTest {
	
	@Resource
	private IPageLogService pageLogService;

	@Test
	public void test() {
		PageLog pageLog=pageLogService.insert("测试");
		
		PageLog temp=pageLogService.getById(pageLog.getId());
		
		assertNotNull(temp);
	}
	
	@Test
	public void testReport() {
		Date reportDate=new Date(System.currentTimeMillis());
		
		List<PageLogReport> result=pageLogService.reportSipderResult(reportDate);
		
		for(PageLogReport report:result) {
			System.out.println(report);
		}
		
	}

}
