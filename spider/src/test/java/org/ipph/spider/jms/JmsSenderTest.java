package org.ipph.spider.jms;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsSenderTest {

	@Resource
	private JmsSender jmsSender;
	@Test
	public void testSendMessage() {
		List<String> appNumberList=new ArrayList<>();
		appNumberList.add("CN201510830923.4");
		appNumberList.add("CN201610562000.X");
		appNumberList.add("CN201710634235.X");
		appNumberList.add("CN201710932510.6");
		
		for(String appNumber:appNumberList) {
			jmsSender.sendByQueue(appNumber,JmsConfiguration.PATENT_FEE_QUEUE_NAME);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(true) {
			
		}
	}

}
