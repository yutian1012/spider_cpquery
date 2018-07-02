package org.ipph.spider.parser;

import org.ipph.spider.entity.PageModel;

public interface IPageParser {
	
	public boolean isValid();
	
	public void parse(PageModel page);
}
