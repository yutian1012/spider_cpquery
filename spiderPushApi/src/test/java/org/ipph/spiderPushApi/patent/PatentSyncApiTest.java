package org.ipph.spiderPushApi.patent;

import static org.junit.Assert.*;

import org.ipph.spiderPushApi.common.ExceptionMsg;
import org.ipph.spiderPushApi.common.Response;
import org.junit.Test;

public class PatentSyncApiTest {

	@Test
	public void testSyncPatent() throws Exception {
		
		PatentSyncApi api=new PatentSyncApi();
		
		String[] appNumbers=new String[] {"CN201610009384.2","CN201711097794.8"};
		
		Response response=api.syncPatent(appNumbers);
		
		assertNotNull(response);
		
		assertTrue(ExceptionMsg.SUCCESS.getCode().equals(response.getRspCode()));
	}
	
	@Test
	public void testSyncPaidFee() throws Exception {
		PatentSyncApi api=new PatentSyncApi();
		String[] appNumbers=new String[] {"CN201610009384.2","CN201711097794.8"};
		
		Response response=api.syncPaidFee(appNumbers);
		
		assertNotNull(response);
		
		assertTrue(ExceptionMsg.SUCCESS.getCode().equals(response.getRspCode()));
	}

}
