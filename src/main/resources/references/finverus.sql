-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.28 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for finverus
CREATE DATABASE IF NOT EXISTS `finverus` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `finverus`;

-- Dumping structure for table finverus.countries
CREATE TABLE IF NOT EXISTS `countries` (
  `country_code` varchar(3) NOT NULL,
  `version` int NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country_name` varchar(30) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.countries: ~0 rows (approximately)
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;

-- Dumping structure for table finverus.event
CREATE TABLE IF NOT EXISTS `event` (
  `event_code` varchar(3) NOT NULL,
  `event_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `UKhvcybi88hxclsja1y8y7yt4hs` (`event_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.event: ~5 rows (approximately)
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` (`event_code`, `event_id`, `description`) VALUES
	('CRT', 1, 'Create'),
	('DEL', 2, 'Delete'),
	('MOD', 3, 'Modify'),
	('APR', 4, 'Approve'),
	('REJ', 5, 'Reject');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;

-- Dumping structure for table finverus.event_permission
CREATE TABLE IF NOT EXISTS `event_permission` (
  `event_id` bigint NOT NULL,
  `permission_event_id` bigint NOT NULL AUTO_INCREMENT,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`permission_event_id`),
  KEY `FK8o5tju1yi57qe9379gle596l1` (`event_id`),
  KEY `FKh8qb94enaa797dkyh4tt1ki0w` (`permission_id`),
  CONSTRAINT `FK8o5tju1yi57qe9379gle596l1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  CONSTRAINT `FKh8qb94enaa797dkyh4tt1ki0w` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.event_permission: ~6 rows (approximately)
/*!40000 ALTER TABLE `event_permission` DISABLE KEYS */;
INSERT INTO `event_permission` (`event_id`, `permission_event_id`, `permission_id`) VALUES
	(3, 1, 6),
	(1, 2, 6),
	(2, 3, 6),
	(3, 4, 7),
	(1, 5, 7),
	(2, 6, 7);
/*!40000 ALTER TABLE `event_permission` ENABLE KEYS */;

-- Dumping structure for table finverus.permission
CREATE TABLE IF NOT EXISTS `permission` (
  `active` int NOT NULL,
  `product_id` int NOT NULL,
  `parent_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.permission: ~4 rows (approximately)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`active`, `product_id`, `parent_id`, `permission_id`, `description`) VALUES
	(1, 1, 0, 4, 'Master Data'),
	(1, 1, 4, 5, 'Reference Data'),
	(1, 1, 5, 6, 'Country'),
	(1, 1, 5, 7, 'Province');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;

-- Dumping structure for table finverus.role
CREATE TABLE IF NOT EXISTS `role` (
  `active` int NOT NULL,
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.role: ~6 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`active`, `role_id`, `role_name`) VALUES
	(1, 1, 'Admin'),
	(1, 2, 'Branch Manager'),
	(1, 3, 'Cash Officer'),
	(1, 4, 'Second Officer'),
	(1, 5, 'Marketting Officer'),
	(1, 6, 'Recovery Officer');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table finverus.role_permission
CREATE TABLE IF NOT EXISTS `role_permission` (
  `active` int NOT NULL,
  `permission_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `role_permission_id` bigint NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(30) NOT NULL,
  PRIMARY KEY (`role_permission_id`),
  UNIQUE KEY `UKmuxvjpy7yamd5e8y3go31fmtg` (`permission_code`),
  KEY `FKf8yllw1ecvwqy3ehyxawqa1qp` (`permission_id`),
  KEY `FKa6jx8n8xkesmjmv6jqug6bg68` (`role_id`),
  CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.role_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;

-- Dumping structure for table finverus.role_permission_event
CREATE TABLE IF NOT EXISTS `role_permission_event` (
  `active` int NOT NULL,
  `event_id` bigint DEFAULT NULL,
  `permission_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `role_permission_event_id` bigint NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(30) NOT NULL,
  PRIMARY KEY (`role_permission_event_id`),
  UNIQUE KEY `UKq0rrt1tcansw6uxyt0ackxgec` (`permission_code`),
  KEY `FK1mom9w37pa288x3rq89nqlqki` (`event_id`),
  KEY `FKj82yvtqpbtrk68ru0jbp6plpm` (`permission_id`),
  KEY `FK46k10km30s2g36rkaap0btsp4` (`role_id`),
  CONSTRAINT `FK1mom9w37pa288x3rq89nqlqki` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  CONSTRAINT `FK46k10km30s2g36rkaap0btsp4` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKj82yvtqpbtrk68ru0jbp6plpm` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.role_permission_event: ~11 rows (approximately)
/*!40000 ALTER TABLE `role_permission_event` DISABLE KEYS */;
INSERT INTO `role_permission_event` (`active`, `event_id`, `permission_id`, `role_id`, `role_permission_event_id`, `permission_code`) VALUES
	(1, NULL, 4, 1, 31, 'CO000010000400000000'),
	(1, NULL, 5, 1, 32, 'CO000010000500004000'),
	(1, 1, 6, 1, 33, 'CO000010000600005CRT'),
	(1, 2, 6, 1, 34, 'CO000010000600005DEL'),
	(1, 3, 6, 1, 35, 'CO000010000600005MOD'),
	(1, 1, 7, 1, 36, 'CO000010000700005CRT'),
	(1, 2, 7, 1, 37, 'CO000010000700005DEL'),
	(1, NULL, 4, 2, 38, 'CO000020000400000000'),
	(1, NULL, 5, 2, 39, 'CO000020000500004000'),
	(1, 1, 6, 2, 40, 'CO000020000600005CRT'),
	(1, 2, 6, 2, 41, 'CO000020000600005DEL');
/*!40000 ALTER TABLE `role_permission_event` ENABLE KEYS */;

-- Dumping structure for table finverus.user_account
CREATE TABLE IF NOT EXISTS `user_account` (
  `active` int NOT NULL,
  `expired` int NOT NULL,
  `locked` int NOT NULL,
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(200) DEFAULT NULL,
  `username` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKcastjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.user_account: ~1 rows (approximately)
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` (`active`, `expired`, `locked`, `user_id`, `password`, `username`) VALUES
	(1, 0, 0, 1, 'zdatai', 'zdatai');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;

-- Dumping structure for table finverus.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `active` int DEFAULT NULL,
  `from_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `to_date` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK7ojmv1m1vrxfl3kvt5bi5ur73` (`user_id`),
  CONSTRAINT `FK7ojmv1m1vrxfl3kvt5bi5ur73` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table finverus.user_role: ~2 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`active`, `from_date`, `id`, `role_id`, `to_date`, `user_id`) VALUES
	(1, '2024-11-21 05:30:00.000000', 1, 1, '2025-11-21 05:30:00.000000', 1),
	(1, '2024-11-21 05:30:00.000000', 2, 2, '2025-11-21 05:30:00.000000', 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
