package org.ipph.spider.common;

import java.util.Map;

public class BaseController {
	protected Response result(ExceptionMsg msg){
    	return new Response(msg);
    }
	
	protected Response result(ExceptionMsg msg,Map<String,Object> models){
    	return new Response(msg,models);
    }
}
