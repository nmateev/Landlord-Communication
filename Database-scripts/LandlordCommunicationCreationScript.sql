CREATE DATABASE  IF NOT EXISTS `landlordcommunication` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `landlordcommunication`;
-- MySQL dump 10.16  Distrib 10.3.9-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: landlordcommunication
-- ------------------------------------------------------
-- Server version	10.3.9-MariaDB

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
-- Table structure for table `chat_messages`
--

DROP TABLE IF EXISTS `chat_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_messages` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `landlord_id` int(11) NOT NULL,
  `chat_session_id` int(11) NOT NULL,
  `date_sent` datetime NOT NULL,
  `message_text` varchar(200) NOT NULL,
  `image_message` blob DEFAULT NULL,
  `is_delivered_to_tenant` tinyint(4) NOT NULL DEFAULT 0,
  `is_delivered_to_landlord` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`message_id`),
  KEY `fk_chat_messages_chat_sessions` (`chat_session_id`),
  KEY `fk_chat_messages_user` (`landlord_id`),
  KEY `fk_chat_messages_users` (`tenant_id`),
  CONSTRAINT `fk_chat_messages_chat_sessions` FOREIGN KEY (`chat_session_id`) REFERENCES `chat_sessions` (`chat_session_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_messages_user` FOREIGN KEY (`landlord_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_messages_users` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chat_sessions`
--

DROP TABLE IF EXISTS `chat_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_sessions` (
  `chat_session_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_created` date NOT NULL,
  `tenant_id` int(11) NOT NULL,
  `landlord_id` int(11) NOT NULL,
  PRIMARY KEY (`chat_session_id`),
  KEY `fk_chat_sessions_users` (`tenant_id`),
  KEY `fk_chat_sessions_user` (`landlord_id`),
  CONSTRAINT `fk_chat_sessions_user` FOREIGN KEY (`landlord_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_sessions_users` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL,
  `landlord_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `date_paid` datetime DEFAULT NULL,
  `card_number` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `fk_payments_users` (`tenant_id`),
  KEY `fk_payments_properties` (`property_id`),
  KEY `fk_payments_user` (`landlord_id`),
  CONSTRAINT `fk_payments_properties` FOREIGN KEY (`property_id`) REFERENCES `properties` (`property_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_payments_user` FOREIGN KEY (`landlord_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_payments_users` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `properties`
--

DROP TABLE IF EXISTS `properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `properties` (
  `property_id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) DEFAULT NULL,
  `landlord_id` int(11) NOT NULL,
  `rent_price` double NOT NULL,
  `due_date` date NOT NULL,
  `is_rent_paid` tinyint(4) NOT NULL DEFAULT 0,
  `property_picture` blob DEFAULT NULL,
  `property_address` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`property_id`),
  KEY `fk_properties_user` (`landlord_id`),
  KEY `fk_properties_users` (`tenant_id`),
  CONSTRAINT `fk_properties_user` FOREIGN KEY (`landlord_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_properties_users` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratings` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `voter_id` int(11) NOT NULL,
  `voted_for_id` int(11) NOT NULL,
  `rating` decimal(2,1) NOT NULL,
  PRIMARY KEY (`rating_id`),
  KEY `fk_ratings_users` (`voter_id`),
  KEY `fk_ratings_user` (`voted_for_id`),
  CONSTRAINT `fk_ratings_user` FOREIGN KEY (`voted_for_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ratings_users` FOREIGN KEY (`voter_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `template_messages`
--

DROP TABLE IF EXISTS `template_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template_messages` (
  `template_message_id` int(11) NOT NULL AUTO_INCREMENT,
  `template_text` varchar(100) NOT NULL,
  `template_type` varchar(45) NOT NULL,
  PRIMARY KEY (`template_message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_password` varchar(100) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `user_type` varchar(45) NOT NULL,
  `user_picture` blob DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-23 15:13:53
