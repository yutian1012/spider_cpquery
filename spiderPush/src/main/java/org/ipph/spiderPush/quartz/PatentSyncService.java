package org.ipph.spiderPush.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spiderPush.patent.service.IPatentInfoService;
import org.ipph.spiderPushApi.patent.service.impl.AbstractThreadPoolPatentSyncServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class PatentSyncService extends AbstractThreadPoolPatentSyncServiceImpl{

	@Resource
	private IPatentInfoService patentInfoService;
	
	@Override
	public Long getTotals() {
		return patentInfoService.getTotals();
	}

	@Override
	public List<String> getPatentInfoListLimit(int start, int size) {
		return patentInfoService.getPatentListLimit(start, size);
	}

}
