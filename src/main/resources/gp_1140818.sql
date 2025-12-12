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
  `calendar_title` varchar(60) NOT NULL,
  `calendar_description` varchar(500) NOT NULL,
  `calendar_start_date` date NOT NULL,
  `calendar_end_date` date NOT NULL,
  `calendar_status` tinyint NOT NULL DEFAULT '1',
  `calendar_photo` varchar(500) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'漢堡',1),(2,'飲料',2),(3,'套餐',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_task`
--

DROP TABLE IF EXISTS `delivery_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_task` (
  `order_no` varchar(60) NOT NULL,
  `delivery_id` int DEFAULT '0',
  `date` date DEFAULT NULL,
  `distance_km` decimal(5,2) DEFAULT '0.00',
  `status` enum('pending','pickup','completed') DEFAULT NULL,
  `is_receive_money` tinyint DEFAULT '0',
  `money` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_task`
--

LOCK TABLES `delivery_task` WRITE;
/*!40000 ALTER TABLE `delivery_task` DISABLE KEYS */;
INSERT INTO `delivery_task` VALUES ('2512101200D07',0,'2025-12-10',NULL,'pending',0,NULL),('2512101200D10',0,'2025-12-10',NULL,'pending',0,NULL);
/*!40000 ALTER TABLE `delivery_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_status`
--

DROP TABLE IF EXISTS `meal_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal_status` (
  `meal_status_id` int NOT NULL AUTO_INCREMENT,
  `meal_status` varchar(60) NOT NULL,
  `estimated_time` int NOT NULL,
  `finish_time` time DEFAULT NULL,
  `orders_id` int NOT NULL,
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
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `option_id` int NOT NULL,
  `option_name` varchar(100) NOT NULL,
  `option_detail` varchar(1000) NOT NULL,
  `category_id` int NOT NULL DEFAULT '0',
  `max_select` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`option_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'加蛋','[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"不加蛋\",\"addPrice\":0}]',1,1),(1,'甜度','[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"半糖\",\"addPrice\":0},{\"option\":\"全堂\",\"addPrice\":0}]',2,1),(2,'加肉','[{\"option\":\"一層肉\",\"addPrice\":20},{\"option\":\"兩層肉\",\"addPrice\":40},{\"option\":\"不加肉\",\"addPrice\":0}]',1,1),(2,'冰塊','[{\"option\":\"去冰\",\"addPrice\":0},{\"option\":\"少冰\",\"addPrice\":0},{\"option\":\"正常冰\",\"addPrice\":0}]',2,1);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `order_details_id` int NOT NULL,
  `order_details` varchar(1000) NOT NULL,
  `order_details_price` int NOT NULL DEFAULT '0',
  `orders_id` int NOT NULL DEFAULT '0',
  `setting_id` int DEFAULT '0',
  PRIMARY KEY (`order_details_id`,`orders_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
<<<<<<< HEAD
INSERT INTO `order_details` VALUES (1,'[{\"categoryId\":1,\"productId\":1,\"productName\":\"牛肉漢堡\",\"productPrice\":180,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]}]',210,1,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]}]',180,2,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"不加肉\",\"addPrice\":0}]}]',165,3,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"兩層肉\",\"addPrice\":40}]}]',205,4,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"兩層肉\",\"addPrice\":40}]}]',205,5,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"兩層肉\",\"addPrice\":40}]}]',205,6,0),(2,'[{\"categoryId\":1,\"productId\":2,\"productName\":\"豬肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]},{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',230,1,1),(2,'[{\"categoryId\":1,\"productId\":2,\"productName\":\"豬肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]},{\"categoryId\":2,\"productId\":2,\"productName\":\"可樂\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"少冰\",\"addPrice\":0}]}]',230,2,1),(2,'[{\"categoryId\":1,\"productId\":4,\"productName\":\"起司漢堡\",\"productPrice\":120,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]}]',150,3,0),(2,'[{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',50,4,0),(2,'[{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',50,5,0),(2,'[{\"categoryId\":1,\"productId\":2,\"productName\":\"豬肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]},{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',210,6,2);
=======
INSERT INTO `order_details` VALUES (1,'[{\"categoryId\":1,\"productId\":1,\"productName\":\"牛肉漢堡\",\"productPrice\":180,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]}]',210,1,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]}]',180,2,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"不加肉\",\"addPrice\":0}]}]',165,3,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"兩層肉\",\"addPrice\":40}]}]',205,4,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"兩層肉\",\"addPrice\":40}]}]',205,5,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"兩層肉\",\"addPrice\":40}]}]',205,6,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"兩層肉\",\"addPrice\":40}]}]',205,7,0),(1,'[{\"categoryId\":1,\"productId\":3,\"productName\":\"雞肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"蔥蛋\",\"addPrice\":15},{\"option\":\"兩層肉\",\"addPrice\":40}]}]',205,10,0),(2,'[{\"categoryId\":1,\"productId\":2,\"productName\":\"豬肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]},{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',230,1,1),(2,'[{\"categoryId\":1,\"productId\":2,\"productName\":\"豬肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]},{\"categoryId\":2,\"productId\":2,\"productName\":\"可樂\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"少冰\",\"addPrice\":0}]}]',230,2,1),(2,'[{\"categoryId\":1,\"productId\":4,\"productName\":\"起司漢堡\",\"productPrice\":120,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]}]',150,3,0),(2,'[{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',50,4,0),(2,'[{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',50,5,0),(2,'[{\"categoryId\":1,\"productId\":2,\"productName\":\"豬肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]},{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',210,6,2),(2,'[{\"categoryId\":1,\"productId\":2,\"productName\":\"豬肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]},{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',210,7,2),(2,'[{\"categoryId\":1,\"productId\":2,\"productName\":\"豬肉漢堡\",\"productPrice\":150,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"起司蛋\",\"addPrice\":10},{\"option\":\"一層肉\",\"addPrice\":20}]},{\"categoryId\":2,\"productId\":1,\"productName\":\"氣泡水\",\"productPrice\":50,\"mealStatus\":\"製作中\",\"detailList\":[{\"option\":\"無糖\",\"addPrice\":0},{\"option\":\"去冰\",\"addPrice\":0}]}]',210,10,2);
>>>>>>> d9e2c474df591fe6fc841c74b40c2d83f1851a7c
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orders_id` int NOT NULL AUTO_INCREMENT,
  `orders_type` varchar(60) NOT NULL,
  `orders_date` date NOT NULL,
  `orders_time` time NOT NULL,
  `total_price` int NOT NULL DEFAULT '0',
  `payment_type` varchar(60) NOT NULL,
  `paid` tinyint NOT NULL,
  `orders_code` varchar(60) DEFAULT NULL,
  `customer_name` varchar(45) DEFAULT NULL,
  `customer_phone` varchar(45) DEFAULT NULL,
  `customer_address` varchar(100) DEFAULT NULL,
  `table_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orders_id`)
<<<<<<< HEAD
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
>>>>>>> d9e2c474df591fe6fc841c74b40c2d83f1851a7c
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
<<<<<<< HEAD
INSERT INTO `orders` VALUES (1,'A','2025-12-06','12:00:00',440,'現金',0,'2512061200A01',NULL,NULL,NULL,'A01'),(2,'D','2025-12-06','12:00:00',410,'信用卡',1,'2512061200D02','kelly','0912345678','高雄市左營區',NULL),(3,'T','2025-12-06','12:00:00',315,'電子支付',1,'2512061200T03','77','0912345677','高雄市前鎮區',NULL),(4,'A','2025-12-06','14:00:00',255,'信用卡',1,'2512061400A04','','','','A01'),(5,'A','2025-12-07','14:00:00',255,'現金',1,'2512071400A05',NULL,NULL,NULL,'A01'),(6,'T','2025-12-07','16:00:00',415,'現金',1,'2512071600T06','47','0912345647',NULL,'');
=======
INSERT INTO `orders` VALUES (1,'A','2025-12-06','12:00:00',440,'現金',0,'2512061200A01',NULL,NULL,NULL,'A01'),(2,'D','2025-12-06','12:00:00',410,'信用卡',1,'2512061200D02','kelly','0912345678','高雄市左營區',NULL),(3,'T','2025-12-06','12:00:00',315,'電子支付',1,'2512061200T03','77','0912345677','高雄市前鎮區',NULL),(4,'A','2025-12-06','14:00:00',255,'信用卡',1,'2512061400A04','','','','A01'),(5,'A','2025-12-07','14:00:00',255,'現金',1,'2512071400A05',NULL,NULL,NULL,'A01'),(6,'T','2025-12-07','16:00:00',415,'現金',1,'2512071600T06','47','0912345647',NULL,''),(7,'D','2025-12-10','12:00:00',415,'信用卡',1,'2512101200D07','47','0912345647','47的家',''),(10,'D','2025-12-10','12:00:00',415,'信用卡',1,'2512101200D10','47','0912345647','47的家','');
>>>>>>> d9e2c474df591fe6fc841c74b40c2d83f1851a7c
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
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
  PRIMARY KEY (`product_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'牛肉漢堡',180,1,'手作牛肉、蛋、生菜、蕃茄、起司、漢堡包','https://example.com/images/burger.jpg','含牛肉、蛋',1),(1,'氣泡水',50,1,'很好喝的氣泡水','https://example.com/images/drink.jpg','',2),(2,'豬肉漢堡',150,1,'手打豬肉、蛋、生菜、蕃茄、起司、漢堡包','https://example.com/images/burger.jpg','含豬肉、蛋',1),(2,'可樂',50,1,'0卡可樂','https://example.com/images/burger.jpg','',2),(3,'雞肉漢堡',150,1,'手打豬肉、蛋、生菜、蕃茄、起司、漢堡包','https://example.com/images/burger.jpg','含豬肉、蛋',1),(4,'起司漢堡',120,1,'雙層起司漢堡','https://example.com/images/burger.jpg','',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
INSERT INTO `reservation` VALUES ('2025-12-05','0912345678','18:30:00','珈',1,3,4,0,'靠窗座位',1,'A01');
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
  `setting_img` varchar(500) NOT NULL,
  `setting_active` tinyint NOT NULL DEFAULT '1',
  `setting_note` varchar(300) DEFAULT NULL,
  `category_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,'聖誕節套餐','[{\"categoryId\":1,\"detailList\":[{\"productId\":1},{\"productId\":2}]},{\"categoryId\":2,\"detailList\":[{\"productId\":1},{\"productId\":2}]}]',200,'/images/settings/vip_package.jpg',0,'期間限定',3),(2,'過年套餐','[{\"categoryId\":1,\"detailList\":[{\"productId\":3},{\"productId\":4}]},{\"categoryId\":2,\"detailList\":[{\"productId\":1},{\"productId\":2}]}]',180,'/images/settings/vip_package.jpg',0,'',3);
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_daily`
--

DROP TABLE IF EXISTS `table_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_daily` (
  `table_daily_date` date NOT NULL,
  `table_daily_status` tinyint NOT NULL DEFAULT '1',
  `table_id` varchar(20) NOT NULL,
  PRIMARY KEY (`table_daily_date`,`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_daily`
--

LOCK TABLES `table_daily` WRITE;
/*!40000 ALTER TABLE `table_daily` DISABLE KEYS */;
INSERT INTO `table_daily` VALUES ('2025-11-26',1,'A04'),('2025-11-30',0,'A04');
/*!40000 ALTER TABLE `table_daily` ENABLE KEYS */;
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
  `length_x` int NOT NULL DEFAULT '40',
  `length_y` int NOT NULL DEFAULT '40',
  `qr_url` varchar(100) NOT NULL,
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` VALUES ('A01','開放中',4,0,20,20,20,'123');
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workstation`
--

LOCK TABLES `workstation` WRITE;
/*!40000 ALTER TABLE `workstation` DISABLE KEYS */;
INSERT INTO `workstation` VALUES (1,'熱食'),(2,'飲料'),(3,'黑暗料理');
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

<<<<<<< HEAD
-- Dump completed on 2025-12-09 13:57:28
=======
-- Dump completed on 2025-12-10 11:40:23
>>>>>>> d9e2c474df591fe6fc841c74b40c2d83f1851a7c
