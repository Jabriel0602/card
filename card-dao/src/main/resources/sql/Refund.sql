-- auto Generated on 2018-04-27 15:35:09 
-- DROP TABLE IF EXISTS refund; 
CREATE TABLE refund(
	order_id BIGINT (15) NOT NULL  COMMENT '订单id',
	refund_id BIGINT (15) NOT NULL DEFAULT -1 COMMENT '退款id',
	refund_type INT (11) NOT NULL DEFAULT -1 COMMENT '退款类型',
	money BIGINT (15) NOT NULL DEFAULT -1 COMMENT '退款金额',
	refund_status INT (11) NOT NULL DEFAULT -1 COMMENT '退款状态',
	created_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
	modified_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '修改时间',
	finish_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '完成时间',
	yn TINYINT (3) NOT NULL DEFAULT 0 COMMENT '是否有效 1 有效 0无效',
	PRIMARY KEY (order_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'refund';
