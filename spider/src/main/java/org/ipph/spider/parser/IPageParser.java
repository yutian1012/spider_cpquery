package org.ipph.spider.parser;

import org.ipph.spider.entity.PageModel;

import us.codecraft.webmagic.Page;

public interface IPageParser {
	
	public boolean isValid();
	
	public PageModel parse(Page page);
}
