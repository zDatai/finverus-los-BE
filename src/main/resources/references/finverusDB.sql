-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: finverus
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `version` int(11) NOT NULL,
  `country_code` varchar(3) NOT NULL,
  `country_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `event_code` varchar(3) NOT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `UKhvcybi88hxclsja1y8y7yt4hs` (`event_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'Create','CRT'),(2,'Delete','DEL');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_permission`
--

DROP TABLE IF EXISTS `event_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_permission` (
  `permission_event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`permission_event_id`),
  KEY `FK8o5tju1yi57qe9379gle596l1` (`event_id`),
  KEY `FKh8qb94enaa797dkyh4tt1ki0w` (`permission_id`),
  CONSTRAINT `FK8o5tju1yi57qe9379gle596l1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  CONSTRAINT `FKh8qb94enaa797dkyh4tt1ki0w` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_permission`
--

LOCK TABLES `event_permission` WRITE;
/*!40000 ALTER TABLE `event_permission` DISABLE KEYS */;
INSERT INTO `event_permission` VALUES (1,NULL,1),(2,1,2),(3,2,2),(4,1,3),(5,NULL,4);
/*!40000 ALTER TABLE `event_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_fields`
--

DROP TABLE IF EXISTS `form_fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_fields` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `field_status` enum('MANDATORY','NICE_TO_HAVE','OPTIONAL') NOT NULL,
  `iscddrequired` bit(1) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdjw4a2id9i60a1gx4pat2bbfv` (`section_id`),
  CONSTRAINT `FKdjw4a2id9i60a1gx4pat2bbfv` FOREIGN KEY (`section_id`) REFERENCES `form_sections` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_fields`
--

LOCK TABLES `form_fields` WRITE;
/*!40000 ALTER TABLE `form_fields` DISABLE KEYS */;
INSERT INTO `form_fields` VALUES (1,NULL,NULL,NULL,1,'Customer_Type','MANDATORY',_binary '',1),(2,NULL,NULL,NULL,1,'is_citizen','MANDATORY',_binary '',1),(3,NULL,NULL,NULL,1,'ID_Type','MANDATORY',_binary '',1),(4,NULL,NULL,NULL,1,'Is_related_to_the_company','MANDATORY',_binary '',1),(5,NULL,NULL,NULL,1,'Description','OPTIONAL',_binary '',1),(6,NULL,NULL,NULL,1,'NIC','MANDATORY',_binary '',1),(7,NULL,NULL,NULL,1,'Confirm_NIC','MANDATORY',_binary '',1),(8,NULL,NULL,NULL,1,'NIC_Registered_Date','MANDATORY',_binary '',1),(9,NULL,NULL,NULL,1,'Onboarding_to','MANDATORY',_binary '',1),(10,NULL,NULL,NULL,1,'Title','MANDATORY',_binary '',1),(11,NULL,NULL,NULL,1,'Gender','MANDATORY',_binary '',1),(12,NULL,NULL,NULL,1,'First_Name','MANDATORY',_binary '',1),(13,NULL,NULL,NULL,1,'Middle_Name','OPTIONAL',_binary '',1),(14,NULL,NULL,NULL,1,'Last_Name','MANDATORY',_binary '',1),(15,NULL,NULL,NULL,1,'Name_with_initial','MANDATORY',_binary '',1),(16,NULL,NULL,NULL,1,'Date_of_Birth','MANDATORY',_binary '',1),(17,NULL,NULL,NULL,1,'Age','MANDATORY',_binary '',1),(18,NULL,NULL,NULL,1,'Civil_Status','MANDATORY',_binary '',1),(19,NULL,NULL,NULL,1,'Reagan','MANDATORY',_binary '',1),(20,NULL,NULL,NULL,1,'Race','MANDATORY',_binary '',1),(21,NULL,NULL,NULL,1,'Nationality','MANDATORY',_binary '',1),(22,NULL,NULL,NULL,1,'Registration_Type','MANDATORY',_binary '',1),(23,NULL,NULL,NULL,1,'Priority','MANDATORY',_binary '',1),(24,NULL,NULL,NULL,1,'Country','MANDATORY',_binary '',1),(25,NULL,NULL,NULL,1,'Is_Dual_Citizenship','OPTIONAL',_binary '',1),(26,NULL,NULL,NULL,1,'Citizenship','MANDATORY',_binary '',1),(27,NULL,NULL,NULL,1,'Country','MANDATORY',_binary '',1),(28,NULL,NULL,NULL,1,'Visa_Expired_Date','MANDATORY',_binary '',1),(29,NULL,NULL,NULL,1,'Passport_No','MANDATORY',_binary '',1),(30,NULL,NULL,NULL,1,'Passport_Issue_Date','MANDATORY',_binary '',1),(31,NULL,NULL,NULL,1,'Passport_Expired_Date','MANDATORY',_binary '',1),(32,NULL,NULL,NULL,1,'Number_Type','MANDATORY',_binary '',2),(33,NULL,NULL,NULL,1,'Ownership','MANDATORY',_binary '',2),(34,NULL,NULL,NULL,1,'Country','MANDATORY',_binary '',2),(35,NULL,NULL,NULL,1,'Number','MANDATORY',_binary '',2),(36,NULL,NULL,NULL,1,'is_Primary','OPTIONAL',_binary '',2),(37,NULL,NULL,NULL,1,'OTP','MANDATORY',_binary '',2),(38,NULL,NULL,NULL,1,'Address_Type','MANDATORY',_binary '',3),(39,NULL,NULL,NULL,1,'Address_Line_1','MANDATORY',_binary '',3),(40,NULL,NULL,NULL,1,'Address_Line_2','OPTIONAL',_binary '\0',3),(41,NULL,NULL,NULL,1,'Address_Line_3','OPTIONAL',_binary '\0',3),(42,NULL,NULL,NULL,1,'Province','MANDATORY',_binary '',3),(43,NULL,NULL,NULL,1,'District','MANDATORY',_binary '',3),(44,NULL,NULL,NULL,1,'City','MANDATORY',_binary '',3),(45,NULL,NULL,NULL,1,'Postal_Code','MANDATORY',_binary '',3),(46,NULL,NULL,NULL,1,'Secretarial','MANDATORY',_binary '',3),(47,NULL,NULL,NULL,1,'Country','MANDATORY',_binary '',3),(48,NULL,NULL,NULL,1,'Media_Type','OPTIONAL',_binary '\0',4),(49,NULL,NULL,NULL,1,'ID','OPTIONAL',_binary '\0',4);
/*!40000 ALTER TABLE `form_fields` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_sections`
--

DROP TABLE IF EXISTS `form_sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_sections` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `form_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5xqtw14bem80n4qn1xjdweo2u` (`form_id`),
  CONSTRAINT `FK5xqtw14bem80n4qn1xjdweo2u` FOREIGN KEY (`form_id`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_sections`
--

LOCK TABLES `form_sections` WRITE;
/*!40000 ALTER TABLE `form_sections` DISABLE KEYS */;
INSERT INTO `form_sections` VALUES (1,NULL,NULL,NULL,1,'basic_information',1),(2,NULL,NULL,NULL,1,'contact_details',2),(3,NULL,NULL,NULL,1,'address_details',2),(4,NULL,NULL,NULL,1,'social_media_and_other',2);
/*!40000 ALTER TABLE `form_sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forms`
--

DROP TABLE IF EXISTS `forms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forms`
--

LOCK TABLES `forms` WRITE;
/*!40000 ALTER TABLE `forms` DISABLE KEYS */;
INSERT INTO `forms` VALUES (1,NULL,NULL,NULL,1,'basic_information_in_progress'),(2,NULL,NULL,NULL,1,'contact_details_in_progress');
/*!40000 ALTER TABLE `forms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,1,'Master Data',0,1),(2,1,'Country',1,1),(3,1,'Province',1,1),(4,1,'District',1,1);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) NOT NULL,
  `role_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,1,'Admin'),(2,1,'Manager');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `role_permission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) NOT NULL,
  `permission_code` varchar(30) NOT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_permission_id`),
  UNIQUE KEY `UKmuxvjpy7yamd5e8y3go31fmtg` (`permission_code`),
  KEY `FKs7sfmug7t6spi7q9xw8td182w` (`permission_id`),
  KEY `FKa6jx8n8xkesmjmv6jqug6bg68` (`role_id`),
  CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKs7sfmug7t6spi7q9xw8td182w` FOREIGN KEY (`permission_id`) REFERENCES `event_permission` (`permission_event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (10,1,'CO000020000100000000',1,2),(12,1,'CO000010000100000000',1,1);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) NOT NULL,
  `expired` int(11) NOT NULL,
  `locked` int(11) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `username` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKcastjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,1,0,0,'zdatai','zdatai');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `from_date` datetime(6) DEFAULT NULL,
  `to_date` datetime(6) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK7ojmv1m1vrxfl3kvt5bi5ur73` (`user_id`),
  CONSTRAINT `FK7ojmv1m1vrxfl3kvt5bi5ur73` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,'2024-11-21 05:30:00.000000','2025-11-21 05:30:00.000000',1,1),(2,1,'2024-11-21 05:30:00.000000','2025-11-21 05:30:00.000000',2,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-28 11:41:44
