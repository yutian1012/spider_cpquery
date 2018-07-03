package org.ipph.spider.parser;

import java.util.List;

import org.ipph.spider.entity.PageModel;

import us.codecraft.webmagic.Page;

public interface IPageParser<T extends PageModel> {
	
	public boolean isValid();
	
	public T parse(Page page) throws Exception;
	
	public List<T> parseList(Page page) throws Exception;
}
