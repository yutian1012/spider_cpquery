package org.ipph.spiderPushApi.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class HttpUtil {
	/**
	 * 发送post请求
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static JSONObject sendHttpPost(String url, String body) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try{
			
			HttpPost httpPost = new HttpPost(url);
			// 设置表单提交编码为UTF-8  
			httpPost.setHeader("ContentType", "application/json;charset=UTF-8");
			
			StringEntity contentEntity=new StringEntity(body,"UTF-8");
			contentEntity.setContentEncoding("UTF-8");    
			contentEntity.setContentType("application/json");
			
			httpPost.setEntity(contentEntity);
			//发送post请求
			HttpResponse response = httpClient.execute(httpPost);  
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity = response.getEntity();
				JSONObject resultJson=JSONObject.fromObject(EntityUtils.toString(entity,"UTF-8"));
				httpPost.abort();//释放资源
				EntityUtils.consume(entity);//销毁资源  
				return resultJson;
			}
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
	    return null;
	}
}
