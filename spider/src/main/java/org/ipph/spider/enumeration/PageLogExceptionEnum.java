package org.ipph.spider.enumeration;

public enum PageLogExceptionEnum {
	CONNECTION_EXCEPTION("访问异常"),PARSER_EXCEPTION("数据解析异常"),OPER_EXCEPTION("保存数据异常");
	
	private String message;
	private PageLogExceptionEnum(String message) {
		this.message=message;
	}
	public String getMessage() {
		return this.message;
	}
}
