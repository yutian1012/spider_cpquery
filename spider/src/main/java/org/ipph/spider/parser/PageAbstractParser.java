package org.ipph.spider.parser;

import java.util.List;

import org.ipph.spider.entity.PageModel;
import org.ipph.spider.exception.SpiderParserException;

import us.codecraft.webmagic.Page;
/**
 * 抽象类中封装异常
 *
 * @param <T>
 */
public abstract class PageAbstractParser<T extends PageModel> implements IPageParser<T>{
	
	/**
	 * 方法空实现--需要子类覆盖实现
	 * @param page
	 * @return
	 * @throws Exception
	 */
	protected T parsePage(Page page)throws Exception{
		return null;
	}
	protected List<T> parsePageList(Page page)throws Exception{
		return null;
	}
	

	@Override
	public T parse(Page page) throws SpiderParserException {
		try {
			return parsePage(page);
		}catch(Exception e) {
			String message=e.getMessage();
			if(null==message) {
				message="数据处理异常";
			}
			
			throw new SpiderParserException(page.getRequest().getUrl(),message);
		}
	}
	
	@Override
	public List<T> parseList(Page page) throws SpiderParserException {
		try {
			return parsePageList(page);
		}catch(Exception e) {
			String message=e.getMessage();
			if(null==message) {
				message="数据处理异常";
			}
			
			throw new SpiderParserException(page.getRequest().getUrl(),message);
		}
	}
	
}
