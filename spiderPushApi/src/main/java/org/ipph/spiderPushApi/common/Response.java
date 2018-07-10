package org.ipph.spiderPushApi.common;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	/** 返回信息码*/
	private String rspCode="000000";
	/** 返回信息内容*/
	private String rspMsg="操作成功";
	
	private String url;//跳转连接
	
	private Map<String,Object> data;//返回数据信息
	
	public Response(ExceptionMsg msg){
		this.rspCode=msg.getCode();
		this.rspMsg=msg.getMsg();
	}
	
	public Response(ExceptionMsg msg,Map<String,Object> data) {
		this.rspCode=msg.getCode();
		this.rspMsg=msg.getMsg();
		this.data=data;
	}
}
