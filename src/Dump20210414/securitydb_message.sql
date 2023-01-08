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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `idm` int(11) NOT NULL AUTO_INCREMENT,
  `nom_user` varchar(45) NOT NULL,
  `nom_admin` varchar(45) NOT NULL,
  `titre` varchar(45) DEFAULT NULL,
  `msg` varchar(300) DEFAULT NULL,
  `date_m` longtext,
  PRIMARY KEY (`idm`,`nom_user`,`nom_admin`),
  KEY `nom_user_idx` (`nom_user`),
  KEY `nom_admin_idx` (`nom_admin`),
  CONSTRAINT `nom_admin` FOREIGN KEY (`nom_admin`) REFERENCES `admin` (`nom`),
  CONSTRAINT `nom_user` FOREIGN KEY (`nom_user`) REFERENCES `user` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (2,'user','admin','Hello','Hello admin','07-04-2021'),(3,'user','admin','si saleh','jé','07-04-2021'),(6,'user','admin','aaa','aaa','12-04-2021'),(7,'user','admin','job','job bonus money','12-04-2021'),(8,'user','admin','jax here','hello im jax new here','12-04-2021'),(9,'user','admin','jax','jax','12-04-2021'),(10,'user','admin','hello jax again','im jax new worker here nice to meet you !','12-04-2021'),(11,'jax','admin','wwwwwwwwwwwww','wwwwwwwwwwwwwwwwwwwwww','12-04-2021'),(12,'jax','admin','job interview','job interview papers ','13-04-2021');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-14  6:43:06
