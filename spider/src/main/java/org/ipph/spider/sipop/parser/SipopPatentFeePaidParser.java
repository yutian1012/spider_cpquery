package org.ipph.spider.sipop.parser;

import java.util.List;

import org.ipph.spider.entity.PageModel;
import org.ipph.spider.parser.IPageParser;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selector;

@Component
public class SipopPatentFeePaidParser implements IPageParser{

	@Override
	public boolean isValid() {
		return false;
	}
	//https://my.oschina.net/anxiaole/blog/782026
	@Override
	public PageModel parse(Page page) {
		/*Json data=page.getJson();
		
		data.get()*/
		JSONObject json=JSONObject.parseObject(page.getRawText());
		
		System.out.println(json.get("code"));
		
		System.out.println("*******************************************");
		return null;
	}

}
