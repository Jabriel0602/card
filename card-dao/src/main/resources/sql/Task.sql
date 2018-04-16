-- auto Generated on 2018-04-16 13:53:30 
-- DROP TABLE IF EXISTS task; 
CREATE TABLE task(
	task_id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT '任务id',
	task_type INT (11) NOT NULL DEFAULT -1 COMMENT '任务类型',
	task_desc VARCHAR (50) NOT NULL DEFAULT '' COMMENT '任务描述',
	order_id BIGINT (15) NOT NULL DEFAULT -1 COMMENT '@NotNull(message = "业务类型不能为空")',
	retry_strategy INT (11) NOT NULL DEFAULT -1 COMMENT '重试策略',
	retry_times INT (11) NOT NULL DEFAULT -1 COMMENT '重试次数',
	max_retry_times INT (11) NOT NULL DEFAULT -1 COMMENT '最大重试次数',
	execute_status INT (11) NOT NULL DEFAULT -1 COMMENT '执行状态',
	next_execute_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '下次重试时间',
	created_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
	modified_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '修改时间',
	operator VARCHAR (50) NOT NULL DEFAULT '' COMMENT '操作人',
	yn TINYINT (3) NOT NULL DEFAULT 0 COMMENT '数据是否有效 0无效 1有效',
	PRIMARY KEY (task_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'task';
