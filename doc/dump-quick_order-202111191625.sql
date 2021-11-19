-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: quick_order
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `management_admin`
--

DROP TABLE IF EXISTS `management_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `management_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '超管id',
  `uname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '超管用户名',
  `pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '超管密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `management_admin`
--

LOCK TABLES `management_admin` WRITE;
/*!40000 ALTER TABLE `management_admin` DISABLE KEYS */;
INSERT INTO `management_admin` VALUES (1,'anxiaolong','123456'),(2,'zhongwei','654321');
/*!40000 ALTER TABLE `management_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qo_internet_customer`
--

DROP TABLE IF EXISTS `qo_internet_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qo_internet_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL COMMENT '平台侧用户id',
  `phone` varchar(100) NOT NULL COMMENT '用户手机号',
  `modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `qo_internet_customer_un` (`uid`,`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qo_internet_customer`
--

LOCK TABLES `qo_internet_customer` WRITE;
/*!40000 ALTER TABLE `qo_internet_customer` DISABLE KEYS */;
INSERT INTO `qo_internet_customer` VALUES (1,'wxcvzokok','13566787732','2021-11-19 16:17:23');
/*!40000 ALTER TABLE `qo_internet_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qo_order_info`
--

DROP TABLE IF EXISTS `qo_order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qo_order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `goods_count` int(11) NOT NULL COMMENT '商品数量',
  `saleDate` date NOT NULL COMMENT '商品销售日期',
  `total_price` int(11) NOT NULL COMMENT '订单总价',
  `uid` varchar(100) NOT NULL COMMENT '用户id',
  `phone` varchar(100) NOT NULL COMMENT '用户手机',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0：新增，1：过期，2：已支付，3：已取消，4：已退款',
  `create_time` datetime NOT NULL COMMENT '订单创建时间',
  `modified_time` datetime NOT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `qo_order_info_un` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qo_order_info`
--

LOCK TABLES `qo_order_info` WRITE;
/*!40000 ALTER TABLE `qo_order_info` DISABLE KEYS */;
INSERT INTO `qo_order_info` VALUES (4,'1001120211110160032001',1,3,'2021-11-19',3600,'wxcvzokok','13566787732',0,'2021-11-19 16:17:23','2021-11-19 16:17:23'),(6,'1001120211110160032002',1,3,'2021-11-19',3600,'wxcvzokok','13566787732',0,'2021-11-19 16:21:38','2021-11-19 16:21:38'),(8,'1001120211110160032003',1,9,'2021-11-19',3600,'wxcvzokok','13566787732',0,'2021-11-19 16:23:26','2021-11-19 16:23:26');
/*!40000 ALTER TABLE `qo_order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qo_partner_key`
--

DROP TABLE IF EXISTS `qo_partner_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qo_partner_key` (
  `pid` int(11) NOT NULL COMMENT '渠道id',
  `key` varchar(100) NOT NULL COMMENT '签名key',
  `partner_name` varchar(100) NOT NULL COMMENT '渠道名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qo_partner_key`
--

LOCK TABLES `qo_partner_key` WRITE;
/*!40000 ALTER TABLE `qo_partner_key` DISABLE KEYS */;
INSERT INTO `qo_partner_key` VALUES (10010,'fadc482e555f44fe8675aaa8052bab3f','支付宝'),(10011,'817d285681a34b3794461ab4b0396d73','微信');
/*!40000 ALTER TABLE `qo_partner_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qo_supplier_goods`
--

DROP TABLE IF EXISTS `qo_supplier_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qo_supplier_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `goods_intro` varchar(100) NOT NULL COMMENT '商品简介',
  `goods_supplier_id` int(11) NOT NULL COMMENT '供应商id',
  `goods_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1：可售 0：不可售',
  `modified_time` datetime NOT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qo_supplier_goods`
--

LOCK TABLES `qo_supplier_goods` WRITE;
/*!40000 ALTER TABLE `qo_supplier_goods` DISABLE KEYS */;
INSERT INTO `qo_supplier_goods` VALUES (1,'酥肉冒菜','油炸过的东西本身都已经有了一种自身的香味，再加上冒菜里面的香味和调料的味道，味道吃起来更加的好吃和多样性',1,1,'2021-11-19 16:11:22'),(2,'红烧牛肉面','堪称最经典的面了，不再好说是哪个地方的面条了，因为全国的面馆都有它，要不，某牌的红烧牛肉方便面为什么经久不息呐',2,1,'2021-11-19 16:11:22'),(3,'盖浇饭','主要特点是饭菜结合，食用方便，既有主食米饭，又有美味菜肴。其菜汤汁浇于饭上面，使米饭更富有口感而备受青睐',3,1,'2021-11-19 16:11:22');
/*!40000 ALTER TABLE `qo_supplier_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qo_supplier_goods_stock`
--

DROP TABLE IF EXISTS `qo_supplier_goods_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qo_supplier_goods_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `goods_price` int(11) NOT NULL COMMENT '价格 单位：分',
  `goods_count` int(11) NOT NULL COMMENT '数量',
  `goods_sale_date` date NOT NULL COMMENT '商品销售日期',
  `modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `qo_supplier_goods_stock_un` (`goods_id`,`goods_sale_date`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qo_supplier_goods_stock`
--

LOCK TABLES `qo_supplier_goods_stock` WRITE;
/*!40000 ALTER TABLE `qo_supplier_goods_stock` DISABLE KEYS */;
INSERT INTO `qo_supplier_goods_stock` VALUES (1,1,1800,0,'2021-11-19','2021-11-19 16:17:22'),(2,2,2390,33,'2021-11-19','2021-11-19 16:17:22'),(3,3,1990,28,'2021-11-19','2021-11-19 16:17:22');
/*!40000 ALTER TABLE `qo_supplier_goods_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qo_supplier_info`
--

DROP TABLE IF EXISTS `qo_supplier_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qo_supplier_info` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商id',
  `supplier_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商编码',
  `supplier_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商名',
  `supplier_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '供应商类型（1：三方 0：自营）',
  `link_man` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `phone_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `bank_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行',
  `bank_account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行账号',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商地址',
  `supplier_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '供应商状态（1：正常 0：禁用）',
  `modified_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`supplier_id`),
  UNIQUE KEY `supplier_code_unique` (`supplier_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qo_supplier_info`
--

LOCK TABLES `qo_supplier_info` WRITE;
/*!40000 ALTER TABLE `qo_supplier_info` DISABLE KEYS */;
INSERT INTO `qo_supplier_info` VALUES (1,'914403007576325798','人民的食堂',1,'张天易','13678767789','招商银行','6576543234567898','成都市武侯区天华二路219号',1,'2021-11-19 16:11:22'),(2,'834373007576326541','潮汕牛肉小火锅',1,'刘德军','18767441123','中国工商银行','6789765456667875','成都市锦江区海棠路45号',1,'2021-11-19 16:11:22'),(3,'786543456543267876','万州烤鱼',1,'张逸飞','13677865676','成都银行','3456765451890762','成都市成华区二仙桥西北路17号',1,'2021-11-19 16:11:22');
/*!40000 ALTER TABLE `qo_supplier_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'quick_order'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-19 16:25:37
