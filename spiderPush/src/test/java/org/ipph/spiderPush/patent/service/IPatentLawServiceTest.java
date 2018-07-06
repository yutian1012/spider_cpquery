package org.ipph.spiderPush.patent.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.annotation.Resource;

import org.ipph.spiderPush.patent.entity.PatentLawModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IPatentLawServiceTest {
	
	@Resource
	private IPatentLawService patentLawService;
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");

	@Test
	public void testAdd() throws ParseException {
		
		PatentLawModel law=new PatentLawModel();
		law.setId(UUID.randomUUID().toString())
			.setAppNumber("CN201510831784.7")
			.setLawDate(sdf.parse("2015.11.25"))
			.setLawStatus("测试数据");
		
		patentLawService.add(law);
		
		PatentLawModel temp=patentLawService.getById(law.getId());
		assertNotNull(temp);
	}

}
