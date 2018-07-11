package org.ipph.spiderPush.patent;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spiderPush.patent.entity.PatentInfo;
import org.ipph.spiderPush.patent.service.IPatentInfoService;
import org.ipph.spiderPushApi.common.ExceptionMsg;
import org.ipph.spiderPushApi.common.Response;
import org.ipph.spiderPushApi.patent.PatentSyncApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatentSyncApiTest {
	
	@Resource
	private IPatentInfoService patentInfoService;

	@Test
	public void testSyncPatent() throws Exception {
		
		PatentSyncApi api=new PatentSyncApi();
		
		//String[] appNumbers=new String[] {"CN201610009384.2","CN201711097794.8"};
		List<PatentInfo> patentList=patentInfoService.getPatentInfoListLimit(0, 10);
		 
		 if(null==patentList||patentList.size()==0) {
			 return;
		 }
		 
		 String[] appNumbers=new String[patentList.size()];
		 
		 for(int i=0;i<patentList.size();i++) {
			 appNumbers[i]=patentList.get(i).getAppNumber();
		 }
		
		Response response=api.syncPatent(appNumbers);
		
		assertNotNull(response);
		
		assertTrue(ExceptionMsg.SUCCESS.getCode().equals(response.getRspCode()));
	}

}
