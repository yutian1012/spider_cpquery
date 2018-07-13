package org.ipph.spider.sipop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ipph.spider.common.BaseController;
import org.ipph.spider.common.ExceptionMsg;
import org.ipph.spider.common.Response;
import org.ipph.spider.sipop.entity.SipopPatentFeePaid;
import org.ipph.spider.sipop.service.ISipopPatentFeePaidService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("patent")
@RestController
public class SipopPatentFeePaidController extends BaseController{
	
	@Resource
	private ISipopPatentFeePaidService sipopPatentFeePaidService;
	
	/**
	 * 传递需要通过的专利申请号
	 * @param appNumbers
	 * @return
	 */
	@RequestMapping(value="/paidFee" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response paidFee(@RequestBody String[] appNumbers) {
		
		if(null==appNumbers||appNumbers.length==0||appNumbers.length>100) {
			return result(ExceptionMsg.ParamError);
		}
		
		List<SipopPatentFeePaid> list=sipopPatentFeePaidService.getByAppNumberIn(appNumbers);
		
		Map<String,Object> response=new HashMap<>();
		response.put("list", list);
		
		return result(ExceptionMsg.SUCCESS,response);
		
	}
}
