CREATE DATABASE  IF NOT EXISTS `securitydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `securitydb`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: securitydb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `info_visit`
--

DROP TABLE IF EXISTS `info_visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `info_visit` (
  `idinfo` int(11) NOT NULL AUTO_INCREMENT,
  `cin_v` int(11) NOT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `enter_t` varchar(30) DEFAULT NULL,
  `exit_t` varchar(30) DEFAULT NULL,
  `datej` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idinfo`,`cin_v`),
  KEY `cin_v_idx` (`cin_v`),
  CONSTRAINT `cin_v` FOREIGN KEY (`cin_v`) REFERENCES `visiteur` (`cin`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info_visit`
--

LOCK TABLES `info_visit` WRITE;
/*!40000 ALTER TABLE `info_visit` DISABLE KEYS */;
INSERT INTO `info_visit` VALUES (18,12254123,'Reason de visit : \n-\ntour !\n\n','07:19:14','07:19:14','13-04-2021'),(19,12254123,'Reason de visit : \n-\ntour !\n\n','07:19:14','07:19:14','13-04-2021'),(20,12456258,'Reason de visit : \n-\ntour !\n\n','07:25:04','07:25:04','13-04-2021');
/*!40000 ALTER TABLE `info_visit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-14  6:43:05
