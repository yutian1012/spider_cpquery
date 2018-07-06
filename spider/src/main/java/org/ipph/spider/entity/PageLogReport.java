package org.ipph.spider.entity;

import org.ipph.spider.enumeration.LogModelEnum;
import org.ipph.spider.enumeration.PageLogStatusEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageLogReport extends PageModel{
	private PageLogStatusEnum status;
	private LogModelEnum model;
	private Integer size;
}
