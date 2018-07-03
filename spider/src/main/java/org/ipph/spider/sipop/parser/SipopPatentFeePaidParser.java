package org.ipph.spider.sipop.parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.ipph.spider.parser.PageAbstractParser;
import org.ipph.spider.sipop.entity.SipopPatentFeePaid;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;

@Component
public class SipopPatentFeePaidParser extends PageAbstractParser<SipopPatentFeePaid>{
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	
	@Override
	public boolean isValid() {
		return false;
	}
	//https://my.oschina.net/anxiaole/blog/782026

	@Override
	protected List<SipopPatentFeePaid> parsePageList(Page page) throws Exception {
		JSONObject json=JSONObject.parseObject(page.getRawText());
		if(null!=json.getBoolean("result")&&json.getBoolean("result")) {
			JSONObject data=json.getJSONObject("data");
			JSONArray arr=data.getJSONArray("zl_paid_info");
			if(null!=arr&&arr.size()>0) {
				List<SipopPatentFeePaid> list=new ArrayList<SipopPatentFeePaid>(arr.size());
				for(int i=0;i<arr.size();i++) {
					JSONObject model=arr.getJSONObject(i);
					if(null!=model) {
						SipopPatentFeePaid sipopPatentFeePaid=new SipopPatentFeePaid();
						sipopPatentFeePaid.setAmount(model.getDouble("zl_paid_amount"))
						.setFeeDate(sdf.parse(model.getString("zl_date")))
						.setFeeName(model.getString("zl_type"))
						.setPayer(model.getString("zl_payer"))
						.setReceiptNo(model.getString("zl_receipt_num"));
						
						list.add(sipopPatentFeePaid);
					}
				}
				return list;
			}
		}
		return null;
	}
	

}
