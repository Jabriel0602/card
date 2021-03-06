-- auto Generated on 2018-03-05 19:51:17 
-- DROP TABLE IF EXISTS config; 
CREATE TABLE `config` (
id INT ( 11 ) UNSIGNED NOT NULL COMMENT '主键id',
order_id VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '订单id',
config_type INT ( 11 ) NOT NULL DEFAULT - 1 COMMENT '配置类别',
`key` VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '配置键值',
`value` VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '配置valule',
operator VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '操作人',
modified_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '修改时间',
created_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
yn TINYINT ( 3 ) NOT NULL DEFAULT 0 COMMENT '数据是否有效 0无效 1有效',
PRIMARY KEY ( id )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT 'config';