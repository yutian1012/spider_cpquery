package org.ipph.spider.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class HttpUtil {
	/**
	 * 解析获取url请求参数
	 * @param url
	 * @param key
	 * @return
	 */
	public static String getRequestParam(String url,String key){
		if(null==url||"".equals(url)) return null;
		
		if(url.indexOf("?")!=-1){
			url=url.substring(url.indexOf("?")+1);
		}
		
		List<String> list=new ArrayList<String>();
		List<NameValuePair> params=URLEncodedUtils.parse(url, Charset.forName("UTF-8"));
		if(null!=params&&params.size()>0){
			for(NameValuePair p:params){
				if(p.getName().equals(key)){
					list.add(p.getValue());
				}
			}
			if(list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}
}
