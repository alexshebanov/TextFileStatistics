-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: txt
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (81,'1.txt'),(82,'anotherfile.txt'),(83,'file.txt'),(84,'2.txt'),(85,'omn.txt');
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_stats`
--

DROP TABLE IF EXISTS `line_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line_stats` (
  `line_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_id` int(11) NOT NULL,
  `line_content` varchar(255) NOT NULL,
  `longest_word` varchar(255) NOT NULL,
  `shortest_word` varchar(255) NOT NULL,
  `line_length` int(11) NOT NULL,
  `average_word_length` double NOT NULL,
  PRIMARY KEY (`line_id`),
  KEY `lines_files_fk` (`file_id`),
  CONSTRAINT `lines_files_fk` FOREIGN KEY (`file_id`) REFERENCES `files` (`file_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_stats`
--

LOCK TABLES `line_stats` WRITE;
/*!40000 ALTER TABLE `line_stats` DISABLE KEYS */;
INSERT INTO `line_stats` VALUES (233,81,'maaaaaaaaagic moments','maaaaaaaaagic','moments',21,10),(234,81,'When two hearts are caring','hearts','two',26,4.4),(235,82,'ASFA rr','ASFA','rr',7,3),(236,82,'фыаы ккп','фыаы','ккп',8,3.5),(237,82,'ZZZZ','ZZZZ','ZZZZ',4,4),(238,83,'One two three','three','One',13,3.6666666666666665),(239,83,'four','four','four',4,4),(240,83,'five six','five','six',8,3.5),(241,83,'seven eight nine ten','seven','ten',20,4.25),(242,84,'another','another','another',7,7),(243,84,'incredible file','incredible','file',15,7),(244,84,'yeaaah','yeaaah','yeaaah',6,6),(245,85,' ,  uio. oefwe! o','oefwe','o',17,3),(246,85,'                 k','k','k',18,1),(247,85,'        ty','ty','ty',10,2),(248,85,'line is empty','','',0,0);
/*!40000 ALTER TABLE `line_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'txt'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-29 15:54:30
