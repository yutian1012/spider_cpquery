package org.ipph.spider.enumeration;

public enum PageLogStatusEnum {
	
	SUCCESS("成功"),FAIL("失败"),PROCESSING("处理中");
	
	private String result;
	private PageLogStatusEnum(String result) {
		this.result=result;
	}
	public String getResult() {
		return this.result;
	}

}
