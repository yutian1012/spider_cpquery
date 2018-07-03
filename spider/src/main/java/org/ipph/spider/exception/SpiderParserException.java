package org.ipph.spider.exception;

import org.ipph.spider.enumeration.PageLogExceptionEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据解析异常
 */
@Setter
@Getter
public class SpiderParserException extends Exception{
	private PageLogExceptionEnum exceptionType=PageLogExceptionEnum.PARSER_EXCEPTION;
	private String url;
	public SpiderParserException(String url,String msg) {
		super(msg);
		this.url=url;
	}
}
