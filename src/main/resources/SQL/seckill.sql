CREATE TABLE `user` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(10) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL COMMENT '注册日期',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录日期',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE user ADD INDEX index_mobile(mobile);

CREATE TABLE `goods` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `goods_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(50) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `goods_detail` text DEFAULT NULL COMMENT '商品详情',
  `goods_price` DOUBLE(8,2) DEFAULT NULL COMMENT '商品价格',
  `goods_stock` int(11) DEFAULT NULL COMMENT '商品数量',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `OrderInfo` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `goods_price` DOUBLE(8,2) DEFAULT NULL COMMENT '商品价格',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态；-1：取消；0：未付款；1：已付款；2：已寄出',
  `pay_date` datetime DEFAULT NULL COMMENT '支付日期',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE OrderInfo ADD INDEX index_userId_goodsId(userId,goodsId); 

CREATE TABLE `seckillGoods` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `stock_count` int(11) DEFAULT NULL COMMENT '秒杀商品的数量',
  `seckill_price` double(4,2) DEFAULT NULL COMMENT '秒杀的价格',
  `start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE seckillGoods ADD INDEX index_goodsId(goodsId); 

