

CREATE TABLE `stock_base` (
  `f57` varchar(255) NOT NULL DEFAULT '000000' COMMENT '股票代码',
  `f58` varchar(255) NOT NULL COMMENT '股票名称',
  `f43` varchar(255) DEFAULT NULL COMMENT '当前价格',
  `f71` varchar(255) DEFAULT NULL COMMENT '均价',
  `f170` varchar(255) DEFAULT NULL COMMENT '涨幅%',
  `f169` varchar(255) DEFAULT NULL COMMENT '涨跌',
  `f60` varchar(255) DEFAULT NULL COMMENT '昨日收盘价',
  `f46` varchar(255) DEFAULT NULL COMMENT '今日开盘价',
  `f44` varchar(255) DEFAULT NULL COMMENT '今日最高价',
  `f45` varchar(255) DEFAULT NULL COMMENT '今日最低价',
  `f47` varchar(255) DEFAULT NULL COMMENT '成交的股票数',
  `f50` varchar(255) DEFAULT NULL COMMENT '量比',
  `f168` varchar(255) DEFAULT NULL COMMENT '换手率',
  `f162` varchar(255) DEFAULT NULL COMMENT '市盈(动)',
  `f116` varchar(255) DEFAULT NULL COMMENT '总市值',
  `f117` varchar(255) DEFAULT NULL COMMENT '流通市值',
  `f167` varchar(255) DEFAULT NULL COMMENT '市净',
  `f135` varchar(255) DEFAULT NULL COMMENT '主力流入',
  `f136` varchar(255) DEFAULT NULL COMMENT '主力流出',
  `f137` varchar(255) DEFAULT NULL COMMENT '主力净流入',
  `f49` varchar(255) DEFAULT NULL COMMENT '外盘',
  `f161` varchar(255) DEFAULT NULL COMMENT '内盘',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`f57`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基础表';

CREATE TABLE `stock_base_history` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `f57` varchar(255) NOT NULL DEFAULT '000000' COMMENT '股票代码',
  `f58` varchar(255) NOT NULL COMMENT '股票名称',
  `f43` varchar(255) DEFAULT NULL COMMENT '当前价格',
  `f71` varchar(255) DEFAULT NULL COMMENT '均价',
  `f170` varchar(255) DEFAULT NULL COMMENT '涨幅%',
  `f169` varchar(255) DEFAULT NULL COMMENT '涨跌',
  `f60` varchar(255) DEFAULT NULL COMMENT '昨日收盘价',
  `f46` varchar(255) DEFAULT NULL COMMENT '今日开盘价',
  `f44` varchar(255) DEFAULT NULL COMMENT '今日最高价',
  `f45` varchar(255) DEFAULT NULL COMMENT '今日最低价',
  `f47` varchar(255) DEFAULT NULL COMMENT '成交的股票数',
  `f50` varchar(255) DEFAULT NULL COMMENT '量比',
  `f168` varchar(255) DEFAULT NULL COMMENT '换手率',
  `f162` varchar(255) DEFAULT NULL COMMENT '市盈(动)',
  `f116` varchar(255) DEFAULT NULL COMMENT '总市值',
  `f117` varchar(255) DEFAULT NULL COMMENT '流通市值',
  `f167` varchar(255) DEFAULT NULL COMMENT '市净',
  `f135` varchar(255) DEFAULT NULL COMMENT '主力流入',
  `f136` varchar(255) DEFAULT NULL COMMENT '主力流出',
  `f137` varchar(255) DEFAULT NULL COMMENT '主力净流入',
  `f49` varchar(255) DEFAULT NULL COMMENT '外盘',
  `f161` varchar(255) DEFAULT NULL COMMENT '内盘',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='基础表';

CREATE TABLE `stock_open_screen` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `f57` varchar(255) NOT NULL DEFAULT '000000' COMMENT '股票代码',
  `f58` varchar(255) NOT NULL COMMENT '股票名称',
  `f43` varchar(255) DEFAULT NULL COMMENT '当前价格',
  `f71` varchar(255) DEFAULT NULL COMMENT '均价',
  `f170` varchar(255) DEFAULT NULL COMMENT '涨幅%',
  `f169` varchar(255) DEFAULT NULL COMMENT '涨跌',
  `f60` varchar(255) DEFAULT NULL COMMENT '昨日收盘价',
  `f46` varchar(255) DEFAULT NULL COMMENT '今日开盘价',
  `f44` varchar(255) DEFAULT NULL COMMENT '今日最高价',
  `f45` varchar(255) DEFAULT NULL COMMENT '今日最低价',
  `f47` varchar(255) DEFAULT NULL COMMENT '成交的股票数',
  `f50` varchar(255) DEFAULT NULL COMMENT '量比',
  `f168` varchar(255) DEFAULT NULL COMMENT '换手率',
  `f162` varchar(255) DEFAULT NULL COMMENT '市盈(动)',
  `f116` varchar(255) DEFAULT NULL COMMENT '总市值',
  `f117` varchar(255) DEFAULT NULL COMMENT '流通市值',
  `f167` varchar(255) DEFAULT NULL COMMENT '市净',
  `f135` varchar(255) DEFAULT NULL COMMENT '主力流入',
  `f136` varchar(255) DEFAULT NULL COMMENT '主力流出',
  `f137` varchar(255) DEFAULT NULL COMMENT '主力净流入',
  `f49` varchar(255) DEFAULT NULL COMMENT '外盘',
  `f161` varchar(255) DEFAULT NULL COMMENT '内盘',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='开盘前';

CREATE TABLE `stock_custom_code` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `f57` varchar(10) NOT NULL DEFAULT '000000' COMMENT '股票代码',
  `price` varchar(10) DEFAULT '0' COMMENT '提醒价格',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='自选股';

