/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.0.87-community-nt : Database - todolist
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`todolist` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `todolist`;

/*Table structure for table `todo` */

DROP TABLE IF EXISTS `todo`;

CREATE TABLE `todo` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(200) default NULL,
  `details` varchar(500) default NULL,
  `start_time` datetime default NULL,
  `end_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `todo` */

insert  into `todo`(`id`,`title`,`details`,`start_time`,`end_time`) values 
(2,'计划2','计划222222','2021-11-22 16:36:17','2021-11-23 16:36:22'),
(4,'123','2131','2021-11-28 16:00:17','2021-11-28 16:00:15'),
(5,'计划3','213','2021-11-28 16:12:04','2021-11-28 16:12:03'),
(6,'计划5','测试','2021-11-28 16:12:30','2021-11-30 14:59:04'),
(7,'计划6','ttt','2021-11-28 16:12:39','2021-11-28 16:12:14'),
(8,'计划7','ttt111','2021-11-28 16:12:45','2021-11-28 16:12:43'),
(9,'111111','111','2021-11-28 16:13:31','2021-11-28 16:13:29'),
(10,'2222222222','111','2021-11-28 16:13:33','2021-11-28 16:13:29'),
(11,'3333333','1111','2021-11-28 16:13:37','2021-11-29 14:59:42'),
(12,'12312321','sdadas11231231231dasds12312sdadas11231231231dasds12312sdadas11231231231dasds12312sdadas11231231231dasds12312sdadas11231231231dasds12312sdadas11231231231dasds12312sdadas11231231231dasds12312sdadas11231231231dasds12312sdadas11231231231dasds12312sdadas1','2021-11-28 16:16:15','2021-11-28 16:16:02'),
(13,'121312','','2021-11-28 16:27:17','2021-11-28 16:27:16'),
(14,'111','222','2021-11-28 20:19:21','2021-11-28 20:20:09'),
(15,'1112','','2021-11-28 20:26:43','2021-11-28 20:27:04'),
(16,'123','','2021-11-29 09:57:19','2021-11-29 15:57:07'),
(17,'1111222','','2021-11-29 09:59:24','2021-11-29 10:00:10'),
(18,'123111','','2021-11-29 10:59:58','2021-11-30 00:00:00'),
(19,'1231111111','','2021-11-29 11:01:14','2021-11-29 14:01:06');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
