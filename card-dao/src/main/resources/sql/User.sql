-- auto Generated on 2018-04-19 17:42:52 
-- DROP TABLE IF EXISTS user; 
CREATE TABLE user(
	user_id BIGINT (15) NOT NULL  COMMENT '主键id',
	user_type VARCHAR (50) NOT NULL DEFAULT '' COMMENT '用户类型',
	user_name VARCHAR (50) NOT NULL DEFAULT '' COMMENT '用户名',
	password VARCHAR (50) NOT NULL DEFAULT '' COMMENT '密码',
	sex VARCHAR (50) NOT NULL DEFAULT '男' COMMENT 'sex',
	phone VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'phone',
	birthday VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'birthday',
	create_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
	modify_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '修改时间',
	operator VARCHAR (50) NOT NULL DEFAULT '' COMMENT '操作人',
	yn TINYINT (3) NOT NULL DEFAULT 0 COMMENT '是否有效 0无效 1有效',
	PRIMARY KEY (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'user';
