package org.ipph.spider.sipop.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ipph.spider.sipop.entity.SipopPatentFeePaid;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SipopPatentFeePaidServiceImplTest {
	
	private String appNumber="2016106768697";
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

	@Resource
	private ISipopPatentFeePaidService sipopPatentFeePaidService;
	
	@Before
	public void testInsert() throws ParseException {
		
		List<SipopPatentFeePaid> list= new ArrayList<>();
		
		SipopPatentFeePaid sipopPatentFeePaid=new SipopPatentFeePaid();
		sipopPatentFeePaid.setAmount(2500D)
			.setFeeDate(sdf.parse("2016-10-12"))
			.setFeeName("发明专利申请审查费")
			.setPayer("宇部兴产株式会社")
			.setReceiptNo("60124939");
		
		list.add(sipopPatentFeePaid);
		
		sipopPatentFeePaidService.batchAdd(list, appNumber);
	}
	
	
	@Test
	public void test() {
		List<SipopPatentFeePaid> list=sipopPatentFeePaidService.getByAppNumber(appNumber);
		assertNotNull(list);
		assertTrue(list.size()>0);
	}

}
