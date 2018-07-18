package org.ipph.spiderPush.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.ipph.spiderPush.patent.service.IPatentInfoService;
import org.ipph.spiderPushApi.common.Response;
import org.ipph.spiderPushApi.patent.service.impl.AbstractThreadPoolPatentPaidFeeSyncServiceImpl;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class PatentFeeSyncService extends AbstractThreadPoolPatentPaidFeeSyncServiceImpl{

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

	@Override
	protected void processResponse(Response response) {
		if(null!=response) {
			Object list=response.getData().get("list");//付费的列表
			if(null!=list) {
				JSONArray jsonArray=JSONArray.fromObject(list);
				if(null==jsonArray||jsonArray.size()==0) {
					return;
				}
				for(int index=0;index<jsonArray.size();index++) {
					JSONObject json=jsonArray.getJSONObject(index);
					
					System.out.println(json.toString());
				}
			}
		}
	}

}
