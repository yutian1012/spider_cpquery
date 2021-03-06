package org.ipph.spider.sipop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatentFeeProcessorTest {
	
	@Resource
	private PatentFeeProcessor patentFeeProcessor;

	@Test
	public void testProcess() {
		
		String url="http://www.sipop.cn/patent-interface-search/patentDetail/queryCostInfo?p_application_docNum=";
		List<String> appNumberList=new ArrayList<>();
		appNumberList.add("CN201510830923.4");
		appNumberList.add("CN201610562000.X");
		appNumberList.add("CN201710634235.X");
		appNumberList.add("CN201710932510.6");
		//向线程中添加url
		for(String appNumber:appNumberList) {
			System.out.println("向线程池中添加新的url"+appNumber);
			patentFeeProcessor.addNewUrl(url+appNumber);
		}
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
