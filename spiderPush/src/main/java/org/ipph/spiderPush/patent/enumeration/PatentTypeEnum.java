package org.ipph.spiderPush.patent.enumeration;
/**
 * 专利类型枚举类
 */
public enum PatentTypeEnum {
	FMZL("发明专利"),SYXX("实用新型"),WGZL("外观专利");
	private String type;
	private PatentTypeEnum(String type) {
		this.type=type;
	}
	public String getType() {
		return this.type;
	}
}
