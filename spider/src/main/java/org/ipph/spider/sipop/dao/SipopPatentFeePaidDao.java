package org.ipph.spider.sipop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.ipph.spider.sipop.entity.SipopPatentFeePaid;

@Mapper
public interface SipopPatentFeePaidDao {
	/**
	 * 批量添加费用
	 * @param paidList
	 * @return
	 */
	public int batchAdd(List<SipopPatentFeePaid> paidList);
	/**
	 * 添加费用
	 * @param paidFee
	 * @return
	 */
	public int add(SipopPatentFeePaid paidFee);
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
	 * 判断数据是否已经存在
	 * @param hash
	 * @return
	 */
	public boolean isExist(String hash);
}
