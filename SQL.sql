CREATE USER 'zhang'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'zhang'@'localhost';


CREATE TABLE `shop_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `username` varchar(50) NOT NULL COMMENT 'user name',
  `password` varchar(50) NOT NULL COMMENT 'password，with MD5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `shop_user` VALUES ('1', 'Tom', 'e10adc3949ba59abbe56e057f20f883e');#pwd:123456

CREATE TABLE `shop_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'item id',
  `name` varchar(100) NOT NULL COMMENT 'item name',
  `image` text COMMENT 'item image url',
  `price` int(11) NOT NULL COMMENT 'item price',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `shop_item` VALUES ('1', 'Effective Java', 'effective_java.jpg', 9), ('2', 'Head First Java', 'head_first_java.jpg', 6), ('3', 'Thinking In Java', 'thinking_in_java.jpg', 8), ('4', 'Head First Design Patterns', 'head_first_design_patterns.jpg', 5), ('5', 'Java Concurrency In Practice', 'java_concurrency_in_practice.jpg', 7), ('6', 'How Tomcat Works', 'how_tomcat_works.jpg', 10);

CREATE TABLE `shop_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `shop_cart` VALUES ('1', '1');

CREATE TABLE `shop_cart_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) DEFAULT NULL COMMENT '商品id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `shop_cart_item` VALUES ('1', '1', '2', 3);