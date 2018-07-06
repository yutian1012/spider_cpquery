package org.ipph.spiderPush.patent.ipms.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.ipph.spiderPush.patent.util.HttpUtil;
import org.ipph.spiderPush.patent.util.PatentCipherUtil;
import org.ipph.spiderPush.patent.util.PatentConstant;
import org.ipph.spiderPush.patent.util.PatentUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component
public class PatentInterfaceHttpClient {
	
	@Value("${ipms.patent.url}")
	private String _ipms_pat_url;
	
	/**
	 * 通过申请号获取专利数据
	 * @param appNumber
	 * @return
	 * @throws Exception 
	 */
	public JSONObject getPatentByAppNumber(String appNumber) throws Exception {
		String dbName=PatentUtil.getPatentInterfaceDbName(appNumber);
		
		return getPatentByAppNumber(appNumber,dbName);
	}
	
	/**
	 * 根据申请号和数据库名获取专利
	 * @param appNumber
	 * @param dbName
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPatentByAppNumber(String appNumber,String dbName)throws Exception{
		return getPatentByAppNumber(appNumber, dbName, null);
	}
	/**
	 * 根据申请号和数据库名，并制定返回的字段列，获取专利
	 * @param appNumber
	 * @param dbName
	 * @param displayCols
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPatentByAppNumber(String appNumber,String dbName,String displayCols)throws Exception{
		return getPatentByAppNumber(appNumber, dbName, displayCols, PatentConstant.PUB_DATE_DESC);
	}
	/**
	 * 根据申请号和数据库名，并制定返回的字段列，以及排序方式，获取专利
	 * @param appNumber
	 * @param dbName
	 * @param displayCols
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPatentByAppNumber(String appNumber,String dbName,String displayCols,String order)throws Exception{
		//封装请求参数
        List<NameValuePair> nvps=new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("from", "0"));  
		nvps.add(new BasicNameValuePair("to", "1")); 
		if(null==dbName){//检索国外专利
			return null;
		}else{
			nvps.add(new BasicNameValuePair("exp","申请号='"+appNumber+"'"));
			nvps.add(new BasicNameValuePair("dbs", dbName));
		}
		if(null!=order&&!"".equals(order)){
			nvps.add(new BasicNameValuePair("order", order));//-表降序
		}else{
			nvps.add(new BasicNameValuePair("order", PatentConstant.PUB_DATE_DESC));//降序
		}
		if(null!=displayCols&&!"".equals(displayCols)){
			nvps.add(new BasicNameValuePair("displayCols", displayCols));
		}
		//请求路径
		
		JSONArray arrayJson=null;
		
		JSONObject resultJson=getByHttpClient(nvps,this._ipms_pat_url);
		
		if(null!=resultJson&&resultJson.get("message").equals("SUCCESS")){
			arrayJson=(JSONArray) resultJson.get("results");
		}
		
		if(null!=arrayJson&&arrayJson.size()>0){
			return arrayJson.getJSONObject(0);
		}
		return null;
	}
	/**
	 * 发送http请求
	 * @param nvps
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private JSONObject getByHttpClient(List<NameValuePair> nvps,String url)throws Exception{
		 HttpClient httpclient = null;
		try{
			RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(20000)
				.setConnectTimeout(20000) .setConnectionRequestTimeout(20000).build();
			httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
			
			HttpPost httpPost = new HttpPost(url);

			//服务器加密时间戳
			Map<String,String> passParam=PatentCipherUtil.getEncodeParam();
			if(passParam.size()>0){
				for(String key:passParam.keySet()){
					nvps.add(new BasicNameValuePair(key, passParam.get(key)));
				}
			}
			
			// 设置表单提交编码为UTF-8  
			httpPost.setHeader("ContentType", HttpUtil._jsonApplication);
			UrlEncodedFormEntity entry = new UrlEncodedFormEntity(nvps, HttpUtil._encode);  
			entry.setContentType(HttpUtil._application);  
			httpPost.setEntity(entry);
			
			//发送post请求
			HttpResponse response = httpclient.execute(httpPost);  
			
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity = response.getEntity();
				JSONObject resultJson=JSONObject.parseObject(EntityUtils.toString(entity,"UTF-8"));
				nvps.clear();
				httpPost.abort();//释放资源
				EntityUtils.consume(entity);//销毁资源  
				return resultJson;
			}
		}finally{
			HttpClientUtils.closeQuietly(httpclient);
		}
	    return null;
	}
}