package org.ipph.spider.sipop.service;

import java.util.List;

import org.ipph.spider.sipop.entity.SipopPatentFeePaid;

public interface ISipopPatentFeePaidService {
	/**
	 * 批量添加费用
	 * @param paidList
	 * @param appNumber
	 * @return
	 */
	public int batchAdd(List<SipopPatentFeePaid> paidList,String appNumber);
	/**
	 * 添加费用
	 * @param paidFee
	 * @param appNumber
	 * @return
	 */
	public int add(SipopPatentFeePaid paidFee,String appNumber);
	/**
	 * 根据申请号加载费用
	 * @param appNumber
	 * @return
	 */
	public List<SipopPatentFeePaid> getByAppNumber(String appNumber);
	/**
	 * 根据申请号删除费用
	 * @param appNumber
	 * @return
	 */
	public int delByAppNumber(String appNumber);
	/**
	 * 通过hash值判断是否已经存在
	 * @param hash
	 * @return
	 */
	public boolean isExist(String hash);
}
