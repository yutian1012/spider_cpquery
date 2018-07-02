
drop database if exists spider_cpquery;
create database spider_cpquery;
use spider_cpquery;

create table cpquery_patent_fee_paid(
	app_number_ varchar(32) not null comment '申请号',
	FEE_NAME_ varchar(64) not null comment '费用名称',
	AMOUNT_ double(8,2) comment '费用金额',
	FEE_DATE_ date not null comment '缴费日期',
	PAYER_ varchar(128) not null comment '付费人',
	RECEIPT_NO_ varchar(64) not null comment '收据号'
) comment '专利已缴费表';