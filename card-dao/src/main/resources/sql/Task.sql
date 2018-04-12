-- auto Generated on 2018-03-05 19:46:57 
-- DROP TABLE IF EXISTS task; 
CREATE TABLE task(
	id INT (11) UNSIGNED NOT NULL  COMMENT '主键',
	biz_type INT (11) NOT NULL DEFAULT -1 COMMENT '任务对应的业务 类型',
	biz_desc VARCHAR (50) NOT NULL DEFAULT '' COMMENT '任务对应的业务 类型描述',
	biz_id VARCHAR (50) NOT NULL DEFAULT '' COMMENT '任务对应的业务 编号',
	task_type INT (11) NOT NULL DEFAULT -1 COMMENT '任务类型',
	task_desc INT (11) NOT NULL DEFAULT -1 COMMENT '任务描述',
	task_id VARCHAR (50) NOT NULL DEFAULT '' COMMENT '任务id',
	retry_strategy INT (11) NOT NULL DEFAULT -1 COMMENT '重试策略',
	retry_times INT (11) NOT NULL DEFAULT -1 COMMENT '重试次数',
	max_retry_times INT (11) NOT NULL DEFAULT -1 COMMENT '最大重试次数',
	execute_status TINYINT (3) NOT NULL DEFAULT 0 COMMENT '执行状态',
	next_execute_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '下次重试时间',
	final_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '任务截止时间',
	created_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
	modified_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '修改时间',
	operator VARCHAR (50) NOT NULL DEFAULT '' COMMENT '操作人',
	yn TINYINT (3) NOT NULL DEFAULT 0 COMMENT '数据是否有效 0无效 1有效',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'task';
