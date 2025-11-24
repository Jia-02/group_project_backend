-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: gp_1140818
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
  `calendar_id` int NOT NULL AUTO_INCREMENT,
  `calendar_title` varchar(60) DEFAULT NULL,
  `calendar_description` varchar(500) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`calendar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_type` varchar(300) NOT NULL,
  `workstation_id` int NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_out`
--

DROP TABLE IF EXISTS `check_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_out` (
  `order_id` varchar(60) NOT NULL,
  `total_price` int NOT NULL DEFAULT '0',
  `payment_type` varchar(60) DEFAULT NULL,
  `payment_time` datetime DEFAULT NULL,
  `is_paid` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_out`
--

LOCK TABLES `check_out` WRITE;
/*!40000 ALTER TABLE `check_out` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_out` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inner_order`
--

DROP TABLE IF EXISTS `inner_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inner_order` (
  `inner_id` varchar(60) NOT NULL,
  `date` date NOT NULL,
  `table_id` varchar(45) NOT NULL,
  PRIMARY KEY (`inner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inner_order`
--

LOCK TABLES `inner_order` WRITE;
/*!40000 ALTER TABLE `inner_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `inner_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_status`
--

DROP TABLE IF EXISTS `meal_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal_status` (
  `meal_status_id` int NOT NULL,
  `meal_status` varchar(60) NOT NULL,
  `estimated_time` int NOT NULL,
  `finish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`meal_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_status`
--

LOCK TABLES `meal_status` WRITE;
/*!40000 ALTER TABLE `meal_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `meal_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option`
--

DROP TABLE IF EXISTS `option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `option` (
  `option_id` int NOT NULL,
  `option_name` varchar(100) NOT NULL,
  `option_detail` varchar(1000) NOT NULL,
  `category_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option`
--

LOCK TABLES `option` WRITE;
/*!40000 ALTER TABLE `option` DISABLE KEYS */;
/*!40000 ALTER TABLE `option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `order_details_id` int NOT NULL,
  `options_note` varchar(1000) DEFAULT NULL,
  `setting_detail` varchar(1000) DEFAULT NULL,
  `order_details_price` int NOT NULL DEFAULT '0',
  `inner_id` varchar(60) DEFAULT '0',
  `take_out_id` varchar(60) DEFAULT '0',
  `setting_id` int DEFAULT '0',
  PRIMARY KEY (`order_details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_price` int NOT NULL DEFAULT '0',
  `product_active` tinyint NOT NULL DEFAULT '1',
  `product_description` varchar(300) NOT NULL,
  `image_url` varchar(300) NOT NULL,
  `product_note` varchar(300) DEFAULT NULL,
  `category_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr`
--

DROP TABLE IF EXISTS `qr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qr` (
  `qr_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(200) NOT NULL,
  `qr_active` tinyint NOT NULL DEFAULT '1',
  `qr_type` varchar(50) DEFAULT NULL,
  `table_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`qr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr`
--

LOCK TABLES `qr` WRITE;
/*!40000 ALTER TABLE `qr` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservation_date` date NOT NULL,
  `reservation_phone` varchar(45) NOT NULL,
  `reservation_time` time NOT NULL,
  `reservation_name` varchar(100) NOT NULL,
  `reservation_adult_count` int NOT NULL DEFAULT '1',
  `reservation_child_count` int DEFAULT '0',
  `reservation_count` int NOT NULL DEFAULT '1',
  `reservation_status` tinyint DEFAULT '0',
  `reservation_note` varchar(200) DEFAULT NULL,
  `child_seat` int DEFAULT '0',
  `table_id` varchar(20) NOT NULL,
  PRIMARY KEY (`reservation_date`,`reservation_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('2025-12-15','0912345677','20:00:00','小明',1,3,4,0,'嬰兒座椅',3,'A03'),('2025-12-15','0912345678','20:00:00','小嘉',1,3,4,0,'沒有要求',0,'A02');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `setting_id` int NOT NULL AUTO_INCREMENT,
  `setting_name` varchar(100) NOT NULL,
  `setting_detail` varchar(1000) NOT NULL,
  `setting_price` int NOT NULL DEFAULT '0',
  `setting_note` varchar(300) DEFAULT NULL,
  `setting_active` tinyint NOT NULL DEFAULT '1',
  `category_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tables` (
  `table_id` varchar(20) NOT NULL,
  `table_status` varchar(100) NOT NULL,
  `capacity` int NOT NULL DEFAULT '2',
  `position_x` int NOT NULL DEFAULT '0',
  `position_y` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` VALUES ('A01','已預約',8,10,50),('A02','可預約',5,10,30),('A03','已預約',4,10,80),('A04','已預約',2,10,100);
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `take_out`
--

DROP TABLE IF EXISTS `take_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `take_out` (
  `take_out_id` varchar(60) NOT NULL,
  `take_out_type` varchar(60) NOT NULL,
  `customer_name` varchar(60) NOT NULL,
  `customer_phone` varchar(60) NOT NULL,
  `customer_address` varchar(100) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`take_out_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `take_out`
--

LOCK TABLES `take_out` WRITE;
/*!40000 ALTER TABLE `take_out` DISABLE KEYS */;
/*!40000 ALTER TABLE `take_out` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workstation`
--

DROP TABLE IF EXISTS `workstation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workstation` (
  `workstation_id` int NOT NULL AUTO_INCREMENT,
  `workstation_name` varchar(45) NOT NULL,
  PRIMARY KEY (`workstation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workstation`
--

LOCK TABLES `workstation` WRITE;
/*!40000 ALTER TABLE `workstation` DISABLE KEYS */;
/*!40000 ALTER TABLE `workstation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-24 16:29:59
