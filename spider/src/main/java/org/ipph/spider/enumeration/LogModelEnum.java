package org.ipph.spider.enumeration;

/**
 * 定义日志模型类型
 */
public enum LogModelEnum {
	NULL("未指定"),PATENTFEE("专利费用");
	private String modelName;
	private LogModelEnum(String modelName) {
		this.modelName=modelName;
	}
	public String getModelName() {
		return this.modelName;
	}
}
