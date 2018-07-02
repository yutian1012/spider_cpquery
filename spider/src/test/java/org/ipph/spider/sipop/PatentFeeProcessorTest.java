package org.ipph.spider.sipop;

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
		String url="http://www.sipop.cn/patent-interface-search/patentDetail/queryCostInfo?p_application_docNum=CN201510830923.4";
		patentFeeProcessor.run(url);
	}

}
