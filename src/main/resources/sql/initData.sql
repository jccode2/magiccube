

INSERT INTO `1000funs`.`shop` (`id`, `code`, `shop_name`, `region_id`, `manager_id`, `detail_address`, `deleted`) VALUES ('1', '440304001', '景田店', '1', '1', '深圳市福田区景田路110号', false);


INSERT INTO `1000funs`.`users` (`id`, `user_name`, `phone`, `email`, `password`,  `default_region_id`, `user_type`, `default_address`) VALUES ('1', 'SuperAdmin', '110', 'SuperAdmin@1000funs.com', 'hello', '1', '3', '深圳市福田区景田路110号');
INSERT INTO `1000funs`.`users` (`id`, `user_name`, `phone`, `email`, `password`,  `default_region_id`, `user_type`, `default_address`) VALUES ('2', '张三', '13512312312', 'zhangshan@1000funs.com', 'hello','1', '1', '深圳市福田区景田路110号');
INSERT INTO `1000funs`.`users` (`id`, `user_name`, `phone`, `email`, `password`,  `default_region_id`, `user_type`, `default_address`) VALUES ('3', '路人甲', '13669877898', 'jia@163.com', 'hello','1','0', '深圳市福田区景田路112号');
INSERT INTO `1000funs`.`users` (`id`, `user_name`, `phone`, `email`, `password`,  `default_region_id`, `user_type`, `default_address`) VALUES ('4', '路人乙', '13362377128', 'yie@163.com', 'hello','1','0', '深圳市福田区景田布尾村10号');
INSERT INTO `1000funs`.`users` (`id`, `user_name`, `phone`, `email`, `password`,  `default_region_id`, `user_type`, `default_address`) VALUES ('5', '路人丙', '13511211311', 'bin@163.com', 'hello','1','0', '深圳市福田区景田区香梅北武警大厦');


INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('1', '10元区', '/web/img/taochan2.jpg', '10元区', 1, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('2', '9元区', '/img/taochan1.jpg', '9元区;', 1, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('3', '8元区', '/web/img/taochan3.jpg', '8元区;', 1, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('4', '7元区', '/web/img/taochan3.jpg', '7元区;', 1, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('5', '6元区', '/web/img/taochan3.jpg', '6元区;', 1, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('6', '例汤区', '/web/img/taochan3.jpg', '例汤区;', 1, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('7', '小吃', '/web/img/taochan3.jpg', '小吃;', 1, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('8', '饮品', '/web/img/taochan3.jpg', '饮品;', 1, false);

INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('9', '25元区', '/web/img/taochan2.jpg', '25元区;高级货', 2, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('10', '20元区', '/img/taochan1.jpg', '20元区;', 2, false);
INSERT INTO `1000funs`.`food_group` (`id`, `group_name`, `image`, `detail`, `type`, `deleted`) VALUES ('11', '18元区', '/web/img/taochan3.jpg', '18元区;', 2, false);


INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('1', '圆椒排骨', '圆椒排骨', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('2', '苦瓜炒蛋', '苦瓜炒蛋', '/web/img/chaixin.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('3', '蒜蓉菜心', '蒜蓉菜心', '/web/img/kuguachaodang.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('4', '蠔油菇絲蟹柳扒節瓜甫', '蠔油菇絲蟹柳扒節瓜甫', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('5', '煎羊扒配羅勒青醬汁', '煎羊扒配羅勒青醬汁', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('6', '青瓜炒猪肝', '青瓜炒猪肝', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('7', '蠔油鮑魚西生菜', '蠔油鮑魚西生菜', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('8', '萝卜牛腩', '萝卜牛腩', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('9', '铁板茄子', '铁板茄子', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('10', '西兰花炒鲜鱿', '西兰花炒鲜鱿', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('11', '蕃茄蛋', '蕃茄蛋', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('12', '菠菜蕃茄千層粉', '菠菜蕃茄千層粉', '/web/img/paigu.jpg', 1, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('13', '蠔油鮑魚西生菜', '蠔油鮑魚西生菜', '/web/img/paigu.jpg', 1, false);

INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('14', '香汁排骨套餐', '香汁排骨套餐', '/web/img/taochan1.jpg', 2, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('15', '七里香肥牛套餐', '七里香肥牛套餐', '/web/img/taochan2.jpg', 2, false);
INSERT INTO `1000funs`.`food` (`id`, `food_name`, `detail`, `image`, `type`, `deleted`) VALUES ('16', '酱汁鸭腿套餐', '酱汁鸭腿套餐', '/web/img/taochan3.jpg', 2, false);


INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (14, 1, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (14, 3, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (14, 11, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (15, 5, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (15, 6, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (15, 10, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (15, 12, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (16, 2, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (16, 4, false);
INSERT INTO `1000funs`.`package_item` (`package_id`, `food_id`, `deleted`) VALUES (16, 9, false);


INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '1', '1', '10', '10', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '2', '1', '10', '10', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '3', '1', '10', '10', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '4', '1', '10', '10', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '5', '1', '10', '10', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '6', '2', '9', '9', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '7', '2', '9', '9', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '8', '2', '9', '9', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '9', '2', '9', '9', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '10', '3', '8', '8', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '11', '3', '8', '8', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '12', '4', '7', '7', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '13', '5', '6', '6', '10', false, false);

INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '14', '9', '25', '25', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '15', '9', '25', '25', '10', false, false);
INSERT INTO `1000funs`.`food_re_shop` (`shop_id`, `food_id`, `group_id`, `origin_price`, `current_price`, `stock`, `droped`, `deleted`) VALUES ('1', '16', '10', '20', '20', '10', false, false);


INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('1', '1', '2013-02-27 09:30:00', '2013-02-27 11:30:00', '3', '深圳市福田区莲花路2075号香丽大厦二楼', '路人甲', '13669877898', '0', '15', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('2', '1', '2013-02-26 10:30:00', '2013-02-26 11:00:00', '3', '深圳市福田区莲花路2075号香丽大厦三楼', '路人甲', '13669877898', '0', '18', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('3', '1', '2013-02-25 09:00:00', '2013-02-25 11:00:00', '3', '深圳市福田区莲花路2075号香丽大厦一楼', '路人甲', '13669877898', '0', '10', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('4', '1', '2013-02-24 09:50:00', '2013-02-24 11:50:00', '3', '深圳市福田区莲花路2075号香丽大厦二楼', '路人甲', '13669877898', '0', '15', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('5', '1', '2013-02-23 12:30:00', '2013-02-23 12:50:00', '3', '深圳市福田区莲花路2075号香丽大厦二楼', '路人甲', '13669877898', '0', '16', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('6', '1', '2013-02-23 11:30:00', '2013-02-22 11:50:00', '3', '深圳市福田区莲花路2075号香丽大厦二楼', '路人甲', '13669877898', '0', '13', false);

INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('7', '1', '2013-02-18 11:30:00', '2013-02-18 11:40:00', '4', '深圳市福田区景莲路110号香景大厦', '路人乙', '13362377128', '1', '13', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('8', '1', '2013-02-20 11:30:00', '2013-02-20 11:40:00', '4', '深圳市福田区景莲路110号香景大厦', '路人乙', '13362377128', '1', '18', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('9', '1', '2013-02-19 12:30:00', '2013-02-19 12:40:00', '5', '深圳市福田区景田布尾村110号', '路人丙', '13511211311', '1', '11', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('10', '1', '2013-02-16 12:30:00', '2013-02-16 12:40:00', '5', '深圳市福田区景田布尾村110号', '路人丙', '13511211311', '2', '13', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('11', '1', '2013-02-15 10:10:00', '2013-02-15 10:50:00', '3', '深圳市福田区莲花路2075号香丽大厦二楼', '路人甲', '13669877898', '1', '15', false);
INSERT INTO `1000funs`.`orders` (`id`, `shop_id`, `create_time`, `except_time`, `user_id`, `address`, `contact`, `phone`, `order_status`, `total_price`, `deleted`) VALUES ('12', '1', '2013-02-14 10:10:00', '2013-02-14 10:50:00', '3', '深圳市福田区莲花路2075号香丽大厦二楼', '路人甲', '13669877898', '1', '17', false);


INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('1', '1', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('1', '2', '2', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('1', '3', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('1', '4', '1', 3, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('1', '5', '3', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('1', '6', '1', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('1', '7', '1', 3, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('2', '9', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('2', '8', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('2', '2', '2', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('2', '4', '1', 1, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('3', '10', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('3', '11', '2', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('3', '12', '2', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('3', '9', '1', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('3', '5', '1', 1, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('4', '10', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('4', '11', '2', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('4', '12', '1', 1, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('5', '1', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('5', '11', '2', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('5', '12', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('5', '10', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('5', '5', '1', 2, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('6', '10', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('6', '11', '2', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('6', '12', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('6', '4', '1', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('6', '5', '1', 3, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('6', '3', '2', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('6', '2', '1', 3, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('7', '5', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('7', '6', '2', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('7', '1', '1', 3, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('7', '3', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('7', '2', '1', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('7', '4', '1', 1, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('8', '6', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('8', '2', '1', 1, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('9', '9', '2', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('9', '5', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('9', '6', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('9', '2', '1', 1, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('10', '6', '2', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('10', '1', '1', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('10', '1', '1', 2, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('10', '1', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('10', '1', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('10', '1', '1', 1, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('11', '11', '2', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('11', '12', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('11', '13', '1', 1, false);

INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('12', '6', '2', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('12', '5', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('12', '3', '1', 1, false);
INSERT INTO `1000funs`.`order_item` (`order_id`, `item_id`, `amount`, `plate`, `deleted`) VALUES ('12', '9', '1', 1, false);


INSERT INTO `config` (`name`,`value`,`class_name`) VALUES ('shop.autoprint','false','java.lang.String');

INSERT INTO `region` (`id`,`code`,`region_name`,`parent_id`,`image`,`has_children`,`full_path`,`deleted`) VALUES (1,'1','香丽大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0);
INSERT INTO `region` (`id`,`code`,`region_name`,`parent_id`,`image`,`has_children`,`full_path`,`deleted`) VALUES (2,'2','妇儿大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0);
INSERT INTO `region` (`id`,`code`,`region_name`,`parent_id`,`image`,`has_children`,`full_path`,`deleted`) VALUES (3,'3','妇女大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0);
INSERT INTO `region` (`id`,`code`,`region_name`,`parent_id`,`image`,`has_children`,`full_path`,`deleted`) VALUES (4,'4','少女大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0);
INSERT INTO `region` (`id`,`code`,`region_name`,`parent_id`,`image`,`has_children`,`full_path`,`deleted`) VALUES (5,'5','悍妇大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0);
