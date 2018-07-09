package org.ipph.spiderPush.patent.controller;

import javax.annotation.Resource;

import org.ipph.spiderPush.common.BaseController;
import org.ipph.spiderPush.common.ExceptionMsg;
import org.ipph.spiderPush.common.Response;
import org.ipph.spiderPush.patent.service.IPatentInfoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("patent")
@RestController
public class PatentInfoController extends BaseController{
	
	@Resource
	private IPatentInfoService patentInfoService;
	
	/**
	 * 传递需要通过的专利申请号
	 * @param appNumbers
	 * @return
	 */
	@RequestMapping(value="/syncPatent" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response syncPatent(@RequestBody String[] appNumbers) {
		
		if(null!=appNumbers&&appNumbers.length>0) {
			patentInfoService.syncPatent(appNumbers);
			return result(ExceptionMsg.SUCCESS);
		}
		return result(ExceptionMsg.ParamError);
	}
}
