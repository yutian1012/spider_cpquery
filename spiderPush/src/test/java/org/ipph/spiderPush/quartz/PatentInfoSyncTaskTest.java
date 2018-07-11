package org.ipph.spiderPush.quartz;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatentInfoSyncTaskTest {
	
	@Resource
	private PatentInfoSyncTask patentInfoSyncTask;

	@Test
	public void test() throws InterruptedException {
		patentInfoSyncTask.syncPatent();
		
		Thread.currentThread().join();
	}

}
