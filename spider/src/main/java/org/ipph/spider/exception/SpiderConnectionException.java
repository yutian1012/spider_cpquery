package org.ipph.spider.exception;

import org.ipph.spider.enumeration.PageLogExceptionEnum;

import lombok.Getter;
import lombok.Setter;


/**
 * 连接异常
 * @author dell2
 *
 */
@Getter
@Setter
public class SpiderConnectionException extends Exception{
	private PageLogExceptionEnum exceptionType=PageLogExceptionEnum.CONNECTION_EXCEPTION;
	private String url;
	
	public SpiderConnectionException(String url,String msg) {
		super(msg);
		this.url=url;
	}
}
