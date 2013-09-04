-- MySQL dump 10.13  Distrib 5.6.10, for Win32 (x86)
--
-- Host: localhost    Database: 1000funs
-- ------------------------------------------------------
-- Server version	5.6.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES ('shop.autoprint','false','java.lang.String');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evaluate`
--

LOCK TABLES `evaluate` WRITE;
/*!40000 ALTER TABLE `evaluate` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,NULL,'圆椒排骨','圆椒排骨','/web/img/paigu.jpg',1,1),(2,NULL,'苦瓜炒蛋','苦瓜炒蛋','/web/img/chaixin.jpg',1,1),(3,NULL,'蒜蓉菜心','蒜蓉菜心','/web/img/kuguachaodang.jpg',1,1),(4,NULL,'蠔油菇絲蟹柳扒節瓜甫','蠔油菇絲蟹柳扒節瓜甫','/web/img/paigu.jpg',1,1),(5,NULL,'煎羊扒配羅勒青醬汁','煎羊扒配羅勒青醬汁','/web/img/paigu.jpg',1,1),(6,NULL,'青瓜炒猪肝','青瓜炒猪肝','/web/img/paigu.jpg',1,1),(7,NULL,'蠔油鮑魚西生菜','蠔油鮑魚西生菜','/web/img/paigu.jpg',1,1),(8,NULL,'萝卜牛腩','萝卜牛腩','/web/img/paigu.jpg',1,1),(9,NULL,'铁板茄子','铁板茄子','/web/img/paigu.jpg',1,1),(10,NULL,'西兰花炒鲜鱿','西兰花炒鲜鱿','/web/img/paigu.jpg',1,1),(11,NULL,'蕃茄蛋','蕃茄蛋','/web/img/paigu.jpg',1,1),(12,NULL,'菠菜蕃茄千層粉','菠菜蕃茄千層粉','/web/img/paigu.jpg',1,1),(13,NULL,'蠔油鮑魚西生菜','蠔油鮑魚西生菜','/web/img/paigu.jpg',1,1),(14,NULL,'香汁排骨套餐','香汁排骨套餐','/web/img/taochan1.jpg',2,0),(15,NULL,'七里香肥牛套餐','七里香肥牛套餐','/web/img/taochan2.jpg',2,0),(16,NULL,'酱汁鸭腿套餐','酱汁鸭腿套餐','/web/img/taochan3.jpg',2,0),(17,NULL,'小培','小培','/web/upload/IMG_0519_20130426204610.jpg',1,0),(18,NULL,'红烧茄子','红烧茄子','/web/upload/paigu_20130427001620.jpg',1,0),(19,NULL,'苦瓜排骨','苦瓜排骨','/web/upload/paigu_20130427001640.jpg',1,0),(20,NULL,'菜心','菜心','/web/upload/chaixin_20130427001702.jpg',1,0),(21,NULL,'苦瓜炒蛋','苦瓜炒蛋','/web/upload/kuguachaodang_20130427001721.jpg',1,0),(22,NULL,'菜心','访问方法','/web/upload/kuguachaodang_20130428001502.jpg',2,1),(23,NULL,'啊啊啊啊','啊啊啊啊','/web/upload/IMG_0486_20130428001546.jpg',1,0),(24,NULL,'fff','aaa','/web/upload/IMG_0552_20130428001559.jpg',1,0),(25,NULL,'古古怪怪','vvvvvvvvvvvvv','/web/upload/IMG_0569_20130428001625.jpg',1,0),(26,NULL,'dd','ee','/web/upload/IMG_0520_20130428011932.jpg',1,0),(27,NULL,'ee','fffaaa','/web/upload/IMG_0530_20130428011940.jpg',1,0),(28,NULL,'hhhh','ffff','/web/upload/IMG_0473_20130428011949.jpg',1,0),(29,NULL,'A1','aaa','/web/upload/55_20130504234041.jpg',2,0),(30,NULL,'B1','fff','/web/upload/IMG_0509_20130506232944.jpg',2,0),(31,NULL,'A3','fewf','/web/upload/IMG_0479_20130506234154.jpg',2,0),(32,NULL,'A2','fsdf','/web/upload/IMG_0476_20130506234221.jpg',2,0),(33,NULL,'A4','fsdffsdf','/web/upload/55_20130506234324.jpg',2,0),(34,NULL,'A5','fsdfsf','/web/upload/IMG_0557_20130506234415.jpg',2,0);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `food_group`
--

LOCK TABLES `food_group` WRITE;
/*!40000 ALTER TABLE `food_group` DISABLE KEYS */;
INSERT INTO `food_group` VALUES (1,'10元区','/web/img/taochan2.jpg','10元区',1,1,0),(2,'9元区','/img/taochan1.jpg','9元区;',1,1,0),(3,'8元区','/web/img/taochan3.jpg','8元区;',1,1,0),(4,'7元区','/web/img/taochan3.jpg','7元区;',1,1,0),(5,'6元区','/web/img/taochan3.jpg','6元区;',1,1,0),(6,'例汤区','/web/img/taochan3.jpg','例汤区;',1,1,0),(7,'小吃','/web/img/taochan3.jpg','小吃;',1,1,0),(8,'饮品','/web/img/taochan3.jpg','饮品;',1,1,0),(9,'25元区','/web/img/taochan2.jpg','25元区;高级货',2,1,0),(10,'20元区','/img/taochan1.jpg','20元区;',2,1,0),(11,'18元区','/web/img/taochan3.jpg','18元区;',2,1,0),(12,'10元区','/web/upload/千方百味最终稿311_43_20130424231857.png','10元区',0,1,0),(13,'10元区','/web/upload/10yuan_20130424232329.png','10元区',0,0,1),(14,'8元区','/web/upload/8yuan_20130424233107.png','8元区',0,0,2),(15,'6元区','/web/upload/6yuan_20130424233126.png','6元区',0,0,3),(16,'4元区','/web/upload/4yuan_20130424233139.png','4元区',0,0,4),(17,'2元区','/web/upload/2yuan_20130424233155.png','2元区',0,0,5),(18,'15元套餐','/web/upload/15taocan_20130504232310.png','15元套餐',0,0,6),(19,'20元套餐','/web/upload/20taocan_20130504232324.png','20元套餐',0,0,7),(20,'25元套餐','/web/upload/25taocan_20130504232338.png','25元套餐',0,0,8);
/*!40000 ALTER TABLE `food_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `food_re_shop`
--

LOCK TABLES `food_re_shop` WRITE;
/*!40000 ALTER TABLE `food_re_shop` DISABLE KEYS */;
INSERT INTO `food_re_shop` VALUES (1,1,1,10,10,10,0,0),(1,2,1,10,10,10,0,0),(1,3,1,10,10,10,0,0),(1,4,1,10,10,10,0,0),(1,5,1,10,10,10,0,0),(1,6,2,9,9,10,0,0),(1,7,2,9,9,10,0,0),(1,8,2,9,9,10,0,0),(1,9,2,9,9,10,0,0),(1,10,3,8,8,10,0,0),(1,11,3,8,8,10,0,0),(1,12,4,7,7,10,0,0),(1,13,5,6,6,10,0,0),(1,14,9,25,25,10,0,0),(1,15,9,25,25,10,0,0),(1,16,10,20,20,10,0,0),(1,17,14,5,4,667,0,0),(1,19,14,10,8,11111,0,0),(1,20,14,2,2,0,0,0),(1,21,14,10,8,342342,0,0),(1,23,15,11,8,2323,0,0),(1,24,16,12,11,1221,0,0),(1,25,15,23,12,12,0,0),(1,27,13,3,3,2323,0,0),(1,28,17,2,2,33,0,0),(1,29,18,18,15,20,0,0),(1,30,19,11,22,2323,0,0),(1,31,18,10,23,22,0,0),(1,32,18,11,3,2323,0,0),(1,33,18,22,33,233,0,0),(1,34,18,22,33,323,0,0);
/*!40000 ALTER TABLE `food_re_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,1,1,1,0),(2,1,2,2,2,0),(3,1,3,1,1,0),(4,1,4,1,3,0),(5,1,5,3,1,0),(6,1,6,1,2,0),(7,1,7,1,3,0),(8,2,9,1,1,0),(9,2,8,1,1,0),(10,2,2,2,1,0),(11,2,4,1,1,0),(12,3,10,1,1,0),(13,3,11,2,2,0),(14,3,12,2,1,0),(15,3,9,1,2,0),(16,3,5,1,1,0),(17,4,10,1,1,0),(18,4,11,2,1,0),(19,4,12,1,1,0),(20,5,1,1,1,0),(21,5,11,2,2,0),(22,5,12,1,1,0),(23,5,10,1,1,0),(24,5,5,1,2,0),(25,6,10,1,1,0),(26,6,11,2,2,0),(27,6,12,1,1,0),(28,6,4,1,2,0),(29,6,5,1,3,0),(30,6,3,2,1,0),(31,6,2,1,3,0),(32,7,5,1,1,0),(33,7,6,2,2,0),(34,7,1,1,3,0),(35,7,3,1,1,0),(36,7,2,1,2,0),(37,7,4,1,1,0),(38,8,6,1,1,0),(39,8,2,1,1,0),(40,9,9,2,1,0),(41,9,5,1,1,0),(42,9,6,1,1,0),(43,9,2,1,1,0),(44,10,6,2,1,0),(45,10,1,1,2,0),(46,10,1,1,2,0),(47,10,1,1,1,0),(48,10,1,1,1,0),(49,10,1,1,1,0),(50,11,11,2,1,0),(51,11,12,1,1,0),(52,11,13,1,1,0),(53,12,6,2,1,0),(54,12,5,1,1,0),(55,12,3,1,1,0),(56,12,9,1,1,0);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_plan`
--

LOCK TABLES `order_plan` WRITE;
/*!40000 ALTER TABLE `order_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_plan_item`
--

LOCK TABLES `order_plan_item` WRITE;
/*!40000 ALTER TABLE `order_plan_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_plan_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,NULL,1,'2013-02-27 09:30:00','2013-02-27 11:30:00',3,NULL,'深圳市福田区莲花路2075号香丽大厦二楼','路人甲','13669877898',0,NULL,NULL,NULL,NULL,15,0,NULL,NULL),(2,NULL,1,'2013-02-26 10:30:00','2013-02-26 11:00:00',3,NULL,'深圳市福田区莲花路2075号香丽大厦三楼','路人甲','13669877898',0,NULL,NULL,NULL,NULL,18,0,NULL,NULL),(3,NULL,1,'2013-02-25 09:00:00','2013-02-25 11:00:00',3,NULL,'深圳市福田区莲花路2075号香丽大厦一楼','路人甲','13669877898',0,NULL,NULL,NULL,NULL,10,0,NULL,NULL),(4,NULL,1,'2013-02-24 09:50:00','2013-02-24 11:50:00',3,NULL,'深圳市福田区莲花路2075号香丽大厦二楼','路人甲','13669877898',0,NULL,NULL,NULL,NULL,15,0,NULL,NULL),(5,NULL,1,'2013-02-23 12:30:00','2013-02-23 12:50:00',3,NULL,'深圳市福田区莲花路2075号香丽大厦二楼','路人甲','13669877898',0,NULL,NULL,NULL,NULL,16,0,NULL,NULL),(6,NULL,1,'2013-02-23 11:30:00','2013-02-22 11:50:00',3,NULL,'深圳市福田区莲花路2075号香丽大厦二楼','路人甲','13669877898',0,NULL,NULL,NULL,NULL,13,0,NULL,NULL),(7,NULL,1,'2013-02-18 11:30:00','2013-02-18 11:40:00',4,NULL,'深圳市福田区景莲路110号香景大厦','路人乙','13362377128',1,NULL,NULL,NULL,NULL,13,0,NULL,NULL),(8,NULL,1,'2013-02-20 11:30:00','2013-02-20 11:40:00',4,NULL,'深圳市福田区景莲路110号香景大厦','路人乙','13362377128',1,NULL,NULL,NULL,NULL,18,0,NULL,NULL),(9,NULL,1,'2013-02-19 12:30:00','2013-02-19 12:40:00',5,NULL,'深圳市福田区景田布尾村110号','路人丙','13511211311',1,NULL,NULL,NULL,NULL,11,0,NULL,NULL),(10,NULL,1,'2013-02-16 12:30:00','2013-02-16 12:40:00',5,NULL,'深圳市福田区景田布尾村110号','路人丙','13511211311',2,NULL,NULL,NULL,NULL,13,0,NULL,NULL),(11,NULL,1,'2013-02-15 10:10:00','2013-02-15 10:50:00',3,NULL,'深圳市福田区莲花路2075号香丽大厦二楼','路人甲','13669877898',1,NULL,NULL,NULL,NULL,15,0,NULL,NULL),(12,NULL,1,'2013-02-14 10:10:00','2013-02-14 10:50:00',3,NULL,'深圳市福田区莲花路2075号香丽大厦二楼','路人甲','13669877898',1,NULL,NULL,NULL,NULL,17,0,NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `package_item`
--

LOCK TABLES `package_item` WRITE;
/*!40000 ALTER TABLE `package_item` DISABLE KEYS */;
INSERT INTO `package_item` VALUES (1,14,1,0),(2,14,3,0),(3,14,11,0),(4,15,5,0),(5,15,6,0),(6,15,10,0),(7,15,12,0),(8,16,2,0),(9,16,4,0),(10,16,9,0),(11,29,21,0),(12,29,25,0),(13,29,24,0),(14,30,17,0),(15,30,19,0),(16,30,20,0),(17,30,21,0),(29,31,25,0),(30,31,23,0),(31,31,28,0),(32,31,19,0),(33,33,17,0),(34,33,20,0),(35,33,25,0),(36,32,20,0),(37,32,19,0),(38,32,27,0),(39,34,23,0),(40,34,17,0),(41,34,27,0);
/*!40000 ALTER TABLE `package_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `plan_rule`
--

LOCK TABLES `plan_rule` WRITE;
/*!40000 ALTER TABLE `plan_rule` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'1','香丽大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0),(2,'2','妇儿大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0),(3,'3','妇女大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0),(4,'4','少女大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0),(5,'5','悍妇大厦',-1,'/web/client/img/region.jpg',NULL,NULL,0);
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'440304001','景田店',1,'1','深圳市福田区景田路110号',0);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `temp_address`
--

LOCK TABLES `temp_address` WRITE;
/*!40000 ALTER TABLE `temp_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `temp_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
-- (SuperAdmin,hello)
INSERT INTO `users` VALUES (1,NULL,'SuperAdmin','110','SuperAdmin@1000funs.com','5d41402abc4b2a76b9719d911017c592',NULL,NULL,1,'深圳市福田区景田路110号',NULL,3,NULL,NULL),(2,NULL,'张三','13512312312','zhangshan@1000funs.com','hello',NULL,NULL,1,'深圳市福田区景田路110号',NULL,1,NULL,NULL),(3,NULL,'路人甲','13669877898','jia@163.com','hello',NULL,NULL,1,'深圳市福田区景田路112号',NULL,0,NULL,NULL),(4,NULL,'路人乙','13362377128','yie@163.com','hello',NULL,NULL,1,'深圳市福田区景田布尾村10号',NULL,0,NULL,NULL),(5,NULL,'路人丙','13511211311','bin@163.com','hello',NULL,NULL,1,'深圳市福田区景田区香梅北武警大厦',NULL,0,NULL,NULL),(6,NULL,'ksfifa',NULL,'','6272004',0,0,0,NULL,'2013-04-11 00:09:34',0,0,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-18 23:41:14

--開業時間和打烊時間
LOCK TABLES `config` WRITE;

INSERT INTO `config` VALUES ('closing-time','20:00','java.lang.String'),('open-t
ime','08:00','java.lang.String'),(
'sunday-close','1','java.lang.String');

UNLOCK TABLES;

