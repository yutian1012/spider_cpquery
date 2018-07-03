package org.ipph.spider.entity;

import org.ipph.spider.enumeration.PageLogExceptionEnum;
import org.ipph.spider.enumeration.PageLogStatusEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain=true)
public class PageLog extends PageModel{
	private String id;
	private String url;
	private String note;
	private PageLogStatusEnum status;
	private PageLogExceptionEnum exceptionType;//异常类型
}
