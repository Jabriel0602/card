-- auto Generated on 2018-04-13 15:48:40
-- DROP TABLE IF EXISTS order;
CREATE TABLE order(
	order_id BIGINT (15) NOT NULL COMMENT '订单id',
	sku_id VARCHAR (50) NOT NULL DEFAULT '' COMMENT '商品id',
	sku_name VARCHAR (50) NOT NULL DEFAULT '' COMMENT '商品名称',
	sku_money BIGINT (15) NOT NULL DEFAULT -1 COMMENT '商品金额',
	card_id VARCHAR (50) NOT NULL DEFAULT '' COMMENT '卡片id',
	vendor_card_id VARCHAR (50) NOT NULL DEFAULT '' COMMENT '供应商卡片id',
	card_type VARCHAR (50) NOT NULL DEFAULT '' COMMENT '卡片类型',
	user_id BIGINT (15) NOT NULL DEFAULT -1 COMMENT '用户',
	phone VARCHAR (50) NOT NULL DEFAULT '' COMMENT '用户手机号',
	submit_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '提单时间',
	pay_no VARCHAR (50) NOT NULL DEFAULT '' COMMENT '支付号',
	money BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'money',
	pay_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '支付时间',
	pay_status INT (11) NOT NULL DEFAULT -1 COMMENT '相关状态信息',
	recharge_status INT (11) NOT NULL DEFAULT -1 COMMENT '充值状态',
	refund_status INT (11) NOT NULL DEFAULT -1 COMMENT '退款状态',
	order_status INT (11) NOT NULL DEFAULT -1 COMMENT '订单状态',
	fina_status INT (11) NOT NULL DEFAULT -1 COMMENT '最终状态',
	created_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
	modify_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '修改时间',
	yn TINYINT (3) NOT NULL DEFAULT 0 COMMENT '是否有效',
	PRIMARY KEY (order_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'order';
