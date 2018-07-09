package org.ipph.spiderPush.patent.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spiderPush.patent.entity.PatentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IPatentInfoServiceTest {
	
	@Resource
	private IPatentInfoService patentInfoService;
	
	@Test
	public void testAdd() {
		
		String appNumber="CN201621370923.7";
		
		PatentInfo patent=new PatentInfo();
		patent.setAppNumber(appNumber);
		
		if(!patentInfoService.isExists(appNumber)) {
			patentInfoService.addPatentInfo(patent);
		}
		
		PatentInfo temp=patentInfoService.getPatentInfo(appNumber);
		
		assertNotNull(temp);
		
		List<PatentInfo> list=patentInfoService.getPatentInfoListLimit(0, 1);
		
		assertNotNull(list);
		
		assertTrue(1==list.size());
		
	}

}
