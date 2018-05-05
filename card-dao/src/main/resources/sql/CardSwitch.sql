-- auto Generated on 2018-05-04 16:07:04 
-- DROP TABLE IF EXISTS card_switch; 
CREATE TABLE card_switch(
	switch_id BIGINT (15) NOT NULL COMMENT 'switchId',
	switch_desc VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'switchDesc',
	switch_status INT (11) NOT NULL DEFAULT -1 COMMENT 'switchStatus',
	switch_status_desc VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'switchStatusDesc',
	created_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
	modified_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '修改时间',
	operator VARCHAR (50) NOT NULL DEFAULT '' COMMENT '操作人',
	PRIMARY KEY (switch_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'card_switch';
