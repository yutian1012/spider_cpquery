drop table if exists cpquery_patent_info;

create table cpquery_patent_info(
	APP_NUMBER_ varchar(32) not null comment '申请号'
) comment '专利数据同步表';