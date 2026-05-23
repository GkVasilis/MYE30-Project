-- MySQL dump 10.13  Distrib 9.6.0, for Win64 (x86_64)
--
-- Host: localhost    Database: mye30_db
-- ------------------------------------------------------
-- Server version	9.6.0

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'a742c5b9-1a35-11f1-83ec-94de80c809eb:1-62401';

--
-- Current Database: `mye30_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `mye30_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mye30_db`;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articles` (
  `article_ID` int NOT NULL,
  `title` varchar(500) NOT NULL,
  `published_year` int NOT NULL,
  PRIMARY KEY (`article_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `author_ID` int NOT NULL,
  `author_name` varchar(255) NOT NULL,
  `article_ID` int NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`author_ID`,`author_name`,`article_ID`),
  KEY `article_ID` (`article_ID`),
  CONSTRAINT `authors_ibfk_1` FOREIGN KEY (`article_ID`) REFERENCES `articles` (`article_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_articles`
--

DROP TABLE IF EXISTS `conference_articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference_articles` (
  `article_ID` int NOT NULL,
  `conference_ID` int NOT NULL,
  `conference_name` varchar(500) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `cdrom` varchar(100) DEFAULT NULL,
  `crossref` varchar(100) DEFAULT NULL,
  `publtype` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `pages` varchar(100) DEFAULT NULL,
  `mdate` varchar(100) DEFAULT NULL,
  `published_year` int NOT NULL,
  `conference_key` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`conference_ID`),
  KEY `article_ID` (`article_ID`),
  CONSTRAINT `conference_articles_ibfk_1` FOREIGN KEY (`article_ID`) REFERENCES `articles` (`article_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conference_articles_ibfk_2` FOREIGN KEY (`conference_ID`) REFERENCES `conferences` (`conference_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_articles`
--

LOCK TABLES `conference_articles` WRITE;
/*!40000 ALTER TABLE `conference_articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `conference_articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_categories`
--

DROP TABLE IF EXISTS `conference_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference_categories` (
  `primaryFoR` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`primaryFoR`,`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_categories`
--

LOCK TABLES `conference_categories` WRITE;
/*!40000 ALTER TABLE `conference_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `conference_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_rankings`
--

DROP TABLE IF EXISTS `conference_rankings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference_rankings` (
  `conference_ID` int NOT NULL,
  `conf_rank_ID` int NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `c_rank` varchar(100) DEFAULT NULL,
  `primaryFoR` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`conf_rank_ID`),
  KEY `conference_ID` (`conference_ID`),
  CONSTRAINT `conference_rankings_ibfk_1` FOREIGN KEY (`conference_ID`) REFERENCES `conferences` (`conference_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_rankings`
--

LOCK TABLES `conference_rankings` WRITE;
/*!40000 ALTER TABLE `conference_rankings` DISABLE KEYS */;
/*!40000 ALTER TABLE `conference_rankings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conferences`
--

DROP TABLE IF EXISTS `conferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conferences` (
  `conference_ID` int NOT NULL,
  `conference_name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`conference_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conferences`
--

LOCK TABLES `conferences` WRITE;
/*!40000 ALTER TABLE `conferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `conferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal_articles`
--

DROP TABLE IF EXISTS `journal_articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `journal_articles` (
  `article_ID` int NOT NULL,
  `title` varchar(500) DEFAULT NULL,
  `journal_ID` int NOT NULL,
  `journal_name` varchar(500) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `cdrom` varchar(100) DEFAULT NULL,
  `crossref` varchar(100) DEFAULT NULL,
  `mdate` varchar(100) DEFAULT NULL,
  `published_year` int NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `pages` varchar(100) DEFAULT NULL,
  `publtype` varchar(100) DEFAULT NULL,
  `journal_key` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`journal_ID`),
  KEY `article_ID` (`article_ID`),
  CONSTRAINT `journal_articles_ibfk_1` FOREIGN KEY (`journal_ID`) REFERENCES `journals` (`journal_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `journal_articles_ibfk_2` FOREIGN KEY (`article_ID`) REFERENCES `articles` (`article_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal_articles`
--

LOCK TABLES `journal_articles` WRITE;
/*!40000 ALTER TABLE `journal_articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `journal_articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal_rankings`
--

DROP TABLE IF EXISTS `journal_rankings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `journal_rankings` (
  `journal_ID` int NOT NULL,
  `j_rank` int NOT NULL,
  `title` varchar(500) NOT NULL,
  `bestSubjectArea` varchar(100) DEFAULT NULL,
  `bestSubjectRank` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `bestCategories` varchar(100) DEFAULT NULL,
  `journal_language` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`journal_ID`,`j_rank`,`title`),
  CONSTRAINT `journal_rankings_ibfk_1` FOREIGN KEY (`journal_ID`) REFERENCES `journals` (`journal_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal_rankings`
--

LOCK TABLES `journal_rankings` WRITE;
/*!40000 ALTER TABLE `journal_rankings` DISABLE KEYS */;
/*!40000 ALTER TABLE `journal_rankings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journals`
--

DROP TABLE IF EXISTS `journals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `journals` (
  `journal_ID` int NOT NULL,
  `journal_name` varchar(255) DEFAULT NULL,
  `publisher` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`journal_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journals`
--

LOCK TABLES `journals` WRITE;
/*!40000 ALTER TABLE `journals` DISABLE KEYS */;
/*!40000 ALTER TABLE `journals` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-23 11:54:05
