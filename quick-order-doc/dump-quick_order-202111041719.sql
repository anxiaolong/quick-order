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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `management_admin`
--

LOCK TABLES `management_admin` WRITE;
/*!40000 ALTER TABLE `management_admin` DISABLE KEYS */;
INSERT INTO `management_admin` VALUES (1,'admin','111111');
/*!40000 ALTER TABLE `management_admin` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qo_supplier_info`
--

LOCK TABLES `qo_supplier_info` WRITE;
/*!40000 ALTER TABLE `qo_supplier_info` DISABLE KEYS */;
INSERT INTO `qo_supplier_info` VALUES (1,'914403007576325798','大家的食堂',1,'张天易','13288889999','招商银行','6576543234567898','成都市武侯区天华二路219号',1,'2021-11-04 16:13:31'),(4,'914403007576325799','update修改后的大家的食堂',1,'修改后-张天易','13288889999','update招商银行','6576543234567898','update成都市武侯区天华二路219号',1,'2021-11-04 17:17:22'),(7,'914403007576325701','大家的食堂',1,'张天易','13288889999','招商银行','6576543234567898','成都市武侯区天华二路219号',1,'2021-11-04 17:14:46');
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

-- Dump completed on 2021-11-04 17:19:59
