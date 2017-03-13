-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: student_management_system_db
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `adminId` varchar(15) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('SEU/FAS/001','Harshana Madusanka','Colombo','harshana@gmail.com','admin','admin');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_information`
--

DROP TABLE IF EXISTS `contact_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_information` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `message` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_information`
--

LOCK TABLES `contact_information` WRITE;
/*!40000 ALTER TABLE `contact_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_registration`
--

DROP TABLE IF EXISTS `exam_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `stu_id` varchar(45) DEFAULT NULL,
  `year` varchar(15) DEFAULT NULL,
  `semester` varchar(15) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_registration`
--

LOCK TABLES `exam_registration` WRITE;
/*!40000 ALTER TABLE `exam_registration` DISABLE KEYS */;
INSERT INTO `exam_registration` VALUES (1,'Harshana Madusanka Jayamaha','SEU/IS/10/PS/104','Year 2','Semester 2','accept'),(2,'Harshana Madusanka Jayamaha','SEU/IS/10/PS/104','Year 1','Semester 1','accept'),(3,'rasika lakruwan','SEU/IS/10/PS/046','Year 3','Semester 2','accept'),(4,'rasika lakruwan','SEU/IS/10/PS/046','Year 2','Semester 2','accept'),(5,'Kawshalya Madushani','SEU/IS/11/PS/100','Year 2','Semester 1','accept'),(6,'Kawshalya Madushani','SEU/IS/11/PS/100','Year 2','Semester 2','accept');
/*!40000 ALTER TABLE `exam_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `messageType` varchar(45) DEFAULT NULL,
  `message` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'important','Unit Exam on Biology'),(2,'important','Practical Exam'),(3,'important','Exam Registeration'),(4,'news','New Course for Computer Science'),(5,'news','Exam will be start in 17 of feb');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `stuId` varchar(20) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telNo` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `acedemicYear` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('SEU/IS/10/PS/046','rasika lakruwan','kotiyakubura, Warakapola','0111234567','abcd@gmail.com','pass out'),('SEU/IS/10/PS/104','Harshana Madusanka Jayamaha','Kohombedeiya , dorawaka','0774231747','jmhmjayamaha@gmail.com','Pass out '),('SEU/IS/11/PS/100','Kawshalya Madushani','Ambepussa , Warakapola','0771234567','abc@gmail.com','fourth Year');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_feedback`
--

DROP TABLE IF EXISTS `student_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(45) DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_feedback`
--

LOCK TABLES `student_feedback` WRITE;
/*!40000 ALTER TABLE `student_feedback` DISABLE KEYS */;
INSERT INTO `student_feedback` VALUES (1,'About Library','need more books and space','Sat Jan 21 20:27:41 IST 2017'),(2,'About Admin','start admin works on time','Sat Jan 21 20:28:18 IST 2017'),(3,'need assistence for the registration','I\'m harshana mad ushanka , I need to submit my registration form','Sat Jan 21 20:36:27 IST 2017'),(4,'about examintaion','can i know the start date of the exam','Sat Jan 21 20:55:35 IST 2017'),(5,'about admin','change the location of the admin','Thu Feb 02 10:42:46 IST 2017');
/*!40000 ALTER TABLE `student_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_has_subject`
--

DROP TABLE IF EXISTS `student_has_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_has_subject` (
  `id` varchar(45) NOT NULL,
  `student_stuId` varchar(20) NOT NULL,
  `subject_subjectId` varchar(10) NOT NULL,
  `result` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`,`student_stuId`,`subject_subjectId`),
  KEY `fk_student_has_subject_subject1_idx` (`subject_subjectId`),
  KEY `fk_student_has_subject_student1_idx` (`student_stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_has_subject`
--

LOCK TABLES `student_has_subject` WRITE;
/*!40000 ALTER TABLE `student_has_subject` DISABLE KEYS */;
INSERT INTO `student_has_subject` VALUES ('1','SEU/IS/10/PS/046','CSM12012','B-'),('2','SEU/IS/10/PS/046','CSM42033','A+'),('3','SEU/IS/10/PS/104','CSM42033','A'),('4','SEU/IS/10/PS/104','CSM12012','A');
/*!40000 ALTER TABLE `student_has_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_login`
--

DROP TABLE IF EXISTS `student_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_login` (
  `password` varchar(45) DEFAULT NULL,
  `student_stuId` varchar(20) NOT NULL,
  PRIMARY KEY (`student_stuId`),
  CONSTRAINT `fk_student_login_student1` FOREIGN KEY (`student_stuId`) REFERENCES `student` (`stuId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_login`
--

LOCK TABLES `student_login` WRITE;
/*!40000 ALTER TABLE `student_login` DISABLE KEYS */;
INSERT INTO `student_login` VALUES ('rasika','SEU/IS/10/PS/046'),('admin','SEU/IS/10/PS/104'),('kawshi','SEU/IS/11/PS/100');
/*!40000 ALTER TABLE `student_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_tasks`
--

DROP TABLE IF EXISTS `student_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `messageType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_tasks`
--

LOCK TABLES `student_tasks` WRITE;
/*!40000 ALTER TABLE `student_tasks` DISABLE KEYS */;
INSERT INTO `student_tasks` VALUES (1,'You should have 80% of attendance to sit for the exam.','new'),(2,'participate for the Career Development program','new'),(3,'Annual Research session','old'),(4,'Final Semester examiniation in february','new'),(5,NULL,NULL),(6,NULL,NULL),(7,'welcome','something'),(8,'welcome','something'),(9,'welcome','something'),(10,'welcome','something'),(11,'welcome','something'),(12,'nothing is possible without me',NULL),(13,'message','messagetype'),(14,'how are u ','important '),(15,'12323123','important '),(16,'Welcome to new batch','new'),(17,'Students have to collect their cloak on 4th of february 2017','new'),(18,'nothing to do','new');
/*!40000 ALTER TABLE `student_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subjectId` varchar(10) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `teacher_teacherId` varchar(15) NOT NULL,
  PRIMARY KEY (`subjectId`,`teacher_teacherId`),
  KEY `fk_course_teacher1_idx` (`teacher_teacherId`),
  CONSTRAINT `fk_course_teacher1` FOREIGN KEY (`teacher_teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('CSM31022','computational arithmatic','SEU/FAS/052'),('CSM45001','Advanced Database Management system','SEU/FAS/023');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacherId` varchar(15) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `telNo` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `qualification` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('SEU/FAS/015','Balasooriya','Digana, Kandy','0312465798','example@gmail.com','Phd'),('SEU/FAS/023','Mohomad Naleer','samanthurei, kalmunei','0111234569','abcde@gmail.com','Phd'),('SEU/FAS/052','Mohemad Hanees','Samanthurei, Kalmunei','0119876541','hanees.al@gmail.com','Mphil');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-13 18:42:18
