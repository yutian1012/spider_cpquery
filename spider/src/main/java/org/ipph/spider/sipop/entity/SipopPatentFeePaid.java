package org.ipph.spider.sipop.entity;

import java.util.Date;

import org.ipph.spider.entity.PageModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 已缴费信息
 */
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain=true)//提供链式方式
@EqualsAndHashCode(callSuper=false,exclude="hash")
public class SipopPatentFeePaid extends PageModel{
	private String appNumber;//申请号
	private String feeName;//费用种类-费用名称
	private Double amount;//费用金额
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date feeDate;//缴费日期
	private String payer;//缴费人姓名
	private String receiptNo;//收据号
	private String hash;//hash值
}
