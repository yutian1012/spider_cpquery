package org.ipph.spiderPushApi.patent;

import static org.junit.Assert.*;

import org.ipph.spiderPushApi.common.ExceptionMsg;
import org.ipph.spiderPushApi.common.Response;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PatentSyncApiTest {

	@Test
	public void testSyncPatent() throws Exception {
		
		PatentSyncApi api=new PatentSyncApi();
		
		String[] appNumbers=new String[] {"CN201610009384.2","CN201711097794.8"};
		
		Response response=api.syncPatent("http://localhost:8001/patent/syncPatent",appNumbers);
		
		assertNotNull(response);
		
		assertTrue(ExceptionMsg.SUCCESS.getCode().equals(response.getRspCode()));
	}
	
	@Test
	public void testSyncPaidFee() throws Exception {
		PatentSyncApi api=new PatentSyncApi();
		String[] appNumbers=new String[] {"CN200480016783.7","CN03128036.6"};
		
		Response response=api.syncPaidFee("http://localhost:8001/patent/paidFee",appNumbers);
		
		assertNotNull(response);
		
		assertTrue(ExceptionMsg.SUCCESS.getCode().equals(response.getRspCode()));
		
		processResultPaidFee(response);
		
	}
	
	/**
	 * 处理数据
	 * @param response
	 */
	private void processResultPaidFee(Response response) {
		Object list=response.getData().get("list");//付费的列表
		assertNotNull(list);
		
		JSONArray jsonArray=JSONArray.fromObject(list);
		assertNotNull(jsonArray);
		assertTrue(jsonArray.size()>0);
		
		for(int index=0;index<jsonArray.size();index++) {
			JSONObject json=jsonArray.getJSONObject(index);
			
			System.out.println(json.toString());
		}
	}

}
