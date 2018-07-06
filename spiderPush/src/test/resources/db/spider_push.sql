drop database if exists spider_cpquery_push;

create database spider_cpquery_push;

drop table if exists patent_law;

create table patent_law(
	id varchar(64) not null primary key,
	APP_NUMBER_ varchar(32) not null comment '申请号',
	LAW_DATE_ date not null comment '法律状态日期',
	LAW_STATUS_ varchar(128) comment '法律状态'
) comment '法律状态表';

drop table if exists patent_law_info;

create table patent_law_info(
	id varchar(64) not null primary key comment '同patent_law共用主键',
	LAW_INFO_ text
) comment '法律状态信息表';