package org.ipph.spider.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//部门实体类
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)//提供链式方式
public class Dept extends PageModel{
	private Long deptNo;//主键
	private String dName;//部门名称
	private String db_source;//来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同的数据库中
}
