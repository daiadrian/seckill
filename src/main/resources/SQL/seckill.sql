CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `mobile` int(11) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(10) DEFAULT NULL,
  `registerDate` date DEFAULT NULL COMMENT '注册日期',
  `lastLoginDate` date DEFAULT NULL COMMENT '最后登录日期',
  `loginCount` int(11) DEFAULT NULL COMMENT '登录次数',
  `createDate` date DEFAULT NULL COMMENT '创建日期',
  `updateDate` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE user ADD INDEX index_mobile(mobile);

CREATE TABLE `goods` (
  `id` int(11) NOT NULL,
  `goodsName` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `goodsTitle` varchar(50) DEFAULT NULL COMMENT '商品标题',
  `goodsImg` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `goodsDetail` text DEFAULT NULL COMMENT '商品详情',
  `goodsPrice` DOUBLE(8,2) DEFAULT NULL COMMENT '商品价格',
  `goodsStock` int(11) DEFAULT NULL COMMENT '商品数量',
  `createDate` date DEFAULT NULL COMMENT '创建日期',
  `updateDate` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `OrderInfo` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `goodsId` int(11) DEFAULT NULL COMMENT '商品ID',
  `goodsName` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `goodsCount` int(11) DEFAULT NULL COMMENT '商品数量',
  `goodsPrice` DOUBLE(8,2) DEFAULT NULL COMMENT '商品价格',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态；-1：取消；0：未付款；1：已付款；2：已寄出',
  `payDate` date DEFAULT NULL COMMENT '支付日期',
  `createDate` date DEFAULT NULL COMMENT '创建日期',
  `updateDate` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE OrderInfo ADD INDEX index_userId_goodsId(userId,goodsId); 

CREATE TABLE `seckillGoods` (
  `id` int(11) NOT NULL,
  `goodsId` int(11) DEFAULT NULL COMMENT '商品ID',
  `stockCount` int(11) DEFAULT NULL COMMENT '秒杀商品的数量',
  `startDate` date DEFAULT NULL COMMENT '开始日期',
  `endDate` date DEFAULT NULL COMMENT '结束日期',
  `createDate` date DEFAULT NULL COMMENT '创建日期',
  `updateDate` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE seckillGoods ADD INDEX index_goodsId(goodsId); 

