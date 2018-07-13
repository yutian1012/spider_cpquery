package org.ipph.spiderPush.patent.ipms.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.ipph.spiderPush.patent.entity.PatentInfo;
import org.ipph.spiderPush.patent.service.IPatentInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatentInterfaceInitDataTest {
	@Resource
	private PatentInterfaceHttpClient patentClient;
	
	@Resource
	private IPatentInfoService patentInfoService;
	
	private ExecutorService threadPool=Executors.newFixedThreadPool(10); 
	
	private Logger logger=org.slf4j.LoggerFactory.getLogger(PatentInterfaceInitDataTest.class);
	
	@Test
	public void testPatentDownload() throws Exception {
		int from=0;
		int size=1000;
		int total=0;
		
		//获取数据集大小
		JSONObject result=getPatentNo(from, 0);
		
		if(null!=result&&result.get("message").equals("SUCCESS")) {
			total=result.getIntValue("total");
		}
		
		while(from<total) {
			logger.info(Thread.currentThread().getName()+" start thread process from "+from+" to "+from+size);
			threadPool.execute(new PatentNoDownload(from,from+size));
			from+=size;
		}
		
		Thread.currentThread().join();
		
	}
	
	/**
	 * 定义线程，处理数据
	 * @author dell2
	 *
	 */
	class PatentNoDownload implements Runnable{
		private int from;
		private int to;
		public PatentNoDownload(int from,int to) {
			this.from=from;
			this.to=to;
		}
		
		@Override
		public void run() {
			logger.info(Thread.currentThread().getName()+ " get patent info from "+this.from+" to "+this.to);
			try {
				JSONObject result=getPatentNo(from, to);
				if(null!=result&&result.get("message").equals("SUCCESS")){
					JSONArray arrayJson=(JSONArray) result.get("results");
					if(null!=arrayJson) {
						processResult(arrayJson);
						arrayJson=null;
					}
				}
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 处理结果集，保存到数据库中
	 * @param result
	 */
	private void processResult(JSONArray result) {
		
		logger.info(Thread.currentThread().getName()+" processing data ....");
		
		if(null!=result&&result.size()>0) {
			List<PatentInfo> patentList=new ArrayList<>(result.size());
			for(int i=0;i<result.size();i++) {
				JSONObject obj=result.getJSONObject(i);
				if(null!=obj) {
					//patentInfoService.addPatentInfo(new PatentInfo().setAppNumber(obj.getString("appNumber")));
					patentList.add(new PatentInfo().setAppNumber(obj.getString("appNumber")));
				}
			}
			if(patentList.size()>0) {
				patentInfoService.batchAdd(patentList);
			}
		}
	}
	/**
	 * 从接口检索获取数据
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	private JSONObject getPatentNo(int from,int to)throws Exception{
		//封装请求参数
		 List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		 nvps.add(new BasicNameValuePair("from", from+""));  
		 nvps.add(new BasicNameValuePair("to", to+"")); 
		 nvps.add(new BasicNameValuePair("dbs", "FMZL,WGZL,SYXX"));
		 nvps.add(new BasicNameValuePair("order", "+公开（公告）日"));//-表降序
		 nvps.add(new BasicNameValuePair("exp","公开（公告）日>='2010.01.01'"));
		 nvps.add(new BasicNameValuePair("displayCols","appNumber"));
		 
		 return patentClient.getByHttpClient(nvps,"http://api.souips.com:8080/ipms-pi/pat");
	}


}
