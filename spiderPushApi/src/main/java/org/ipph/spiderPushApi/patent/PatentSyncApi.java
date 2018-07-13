package org.ipph.spiderPushApi.patent;

import org.ipph.spiderPushApi.common.Response;
import org.ipph.spiderPushApi.util.HttpUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PatentSyncApi {
	/**
	 * 同步专利
	 * @param appNumbers
	 * @return
	 * @throws Exception
	 */
	public Response syncPatent(String[] appNumbers) throws Exception {
		
		//String url="http://localhost:8001/patent/syncPatent";
		String url="http://114.251.8.233:8001/patent/syncPatent";
		
		return getResponse(url, appNumbers);
	}
	
	/**
	 * 费用检索
	 * @param appNumbers
	 * @return
	 * @throws Exception
	 */
	public Response syncPaidFee(String[] appNumbers) throws Exception {
		
		String url="http://localhost:8001/patent/paidFee";
		
		return getResponse(url, appNumbers);
		
	}
	/**
	 * 获取响应数据
	 * @param url
	 * @param appNumbers
	 * @return
	 * @throws Exception
	 */
	private Response getResponse(String url,String[] appNumbers) throws Exception {
		Response response=null;
		
		if(null==appNumbers||appNumbers.length==0) {
			return null;
		}
		
		JSONArray array=JSONArray.fromObject(appNumbers);
		
		if(null!=array) {
			String body=array.toString();
			JSONObject result=HttpUtil.sendHttpPost(url, body);
			if(null!=result) {
				response= (Response) JSONObject.toBean(result, Response.class);
			}
		}
		
		return response;
	}
}
