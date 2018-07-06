package org.ipph.spiderPush.patent.ipms.client;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatentInterfaceHttpClientTest {
	
	@Resource
	private PatentInterfaceHttpClient patentClient;

	@Test
	public void test() throws Exception {
		String appNumber="CN201510831784.7";
		
		JSONObject result=patentClient.getPatentByAppNumber(appNumber);
		
		assertNotNull(result);
		
		System.out.println(result.toString());
	}

}
