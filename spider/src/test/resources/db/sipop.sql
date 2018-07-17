
drop database if exists spider_cpquery;
create database spider_cpquery;
use spider_cpquery;

drop table if exists cpquery_patent_fee_paid;

create table cpquery_patent_fee_paid(
	app_number_ varchar(32) not null comment '申请号',
	FEE_NAME_ varchar(64) not null comment '费用名称',
	AMOUNT_ double(8,2) comment '费用金额',
	FEE_DATE_ date not null comment '缴费日期',
	PAYER_ varchar(128) not null comment '付费人',
	RECEIPT_NO_ varchar(64) not null comment '收据号'
) comment '专利已缴费表';

alter table cpquery_patent_fee_paid add column HASH_ varchar(64) not null comment '对象hash值';

alter table cpquery_patent_fee_paid add constraint app_number_unique unique(app_number_);

drop table if exists page_log;

create table page_log(
	id varchar(64) not null primary key comment '日志主键字段',
	url varchar(1024) not null comment '爬取地址',
	note text comment '异常信息',
	status varchar(32) not null comment '状态',
	exceptionType varchar(32) comment '异常类型',
	createDate date not null comment '创建日期'
) comment '爬虫日志表';

drop table if exists cpquery_patent_info;

create table cpquery_patent_info(
	APP_NUMBER_ varchar(32) not null comment '申请号'
) comment '专利数据同步表';

alter table cpquery_patent_info add INDEX app_number_index (app_number_);