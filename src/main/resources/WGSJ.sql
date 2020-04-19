/*
SQLyog Ultimate v8.32 
MySQL - 5.7.28-log : Database - wgsj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wgsj` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wgsj`;

/*Table structure for table `ad` */

DROP TABLE IF EXISTS `ad`;

CREATE TABLE `ad` (
  `AD_ID` varchar(36) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  `IMAGE_URL` varchar(100) NOT NULL,
  `CLICK_URL` varchar(100) NOT NULL DEFAULT '/',
  `DATE` varchar(10) NOT NULL,
  `QUEUE` int(10) NOT NULL,
  `IS_ABANDON` int(1) NOT NULL DEFAULT '0',
  `TYPE` varchar(10) NOT NULL DEFAULT 'banner',
  PRIMARY KEY (`AD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `ADDRESS_ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `CONSIGNEE` varchar(36) NOT NULL,
  `MOBILE` varchar(11) NOT NULL,
  `TRANSPORT_DAY` varchar(10) NOT NULL,
  PRIMARY KEY (`ADDRESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `ADMIN_ID` varchar(36) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `AVATAR` varchar(255) NOT NULL,
  `PWD` varchar(20) NOT NULL DEFAULT 'admin',
  `TIME` varchar(20) NOT NULL,
  `SESSION_ID` varchar(40) NOT NULL,
  `TEL` varchar(11) NOT NULL DEFAULT '1',
  `EMAIL` varchar(40) NOT NULL DEFAULT '1606962606@qq.com',
  `IS_ROOT` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `AUTHOR_ID` varchar(36) NOT NULL,
  `NAME` varchar(36) NOT NULL,
  PRIMARY KEY (`AUTHOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `author_book` */

DROP TABLE IF EXISTS `author_book`;

CREATE TABLE `author_book` (
  `AB_ID` varchar(36) NOT NULL,
  `AUTHOR_ID` varchar(36) NOT NULL,
  `BOOK_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`AB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `BOOK_ID` varchar(36) NOT NULL,
  `CLASS_ID` varchar(36) NOT NULL,
  `NAME` varchar(36) NOT NULL,
  `INTRO` varchar(140) NOT NULL,
  `PRICE_T` double NOT NULL,
  `PRICE_N` double NOT NULL,
  `CHARGE` double NOT NULL,
  `STAR` double NOT NULL,
  `STOCK` int(10) NOT NULL,
  `REVIEWS_T` int(5) NOT NULL,
  `SALES_T` int(5) NOT NULL,
  `SALES_M` int(5) NOT NULL,
  `PREVIEW_URL` varchar(100) NOT NULL,
  `TIME` varchar(20) NOT NULL,
  `FULL_NAME` varchar(100) NOT NULL,
  `ISBN` varchar(20) DEFAULT NULL,
  `PUBLISH_DATE` varchar(10) NOT NULL,
  `yin_ci` int(10) DEFAULT '1',
  `PAGE_TOTAL` int(10) DEFAULT NULL,
  `CHARACTER_TOTAL` int(10) DEFAULT NULL,
  `kai_ben` varchar(10) DEFAULT '32',
  `PAPER_TYPE` varchar(10) NOT NULL,
  `bao_zhuang` varchar(10) NOT NULL,
  `tao_zhuang` varchar(10) NOT NULL,
  PRIMARY KEY (`BOOK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `book_detail` */

DROP TABLE IF EXISTS `book_detail`;

CREATE TABLE `book_detail` (
  `BOOK_D` varchar(40) NOT NULL,
  `BOOK_ID` varchar(36) NOT NULL,
  `DETAIL_URL` varchar(100) NOT NULL,
  PRIMARY KEY (`BOOK_D`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `book_intro` */

DROP TABLE IF EXISTS `book_intro`;

CREATE TABLE `book_intro` (
  `BOOK_I` varchar(40) NOT NULL,
  `BOOK_ID` varchar(36) NOT NULL,
  `INTRO_URL` varchar(100) NOT NULL,
  PRIMARY KEY (`BOOK_I`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `classification` */

DROP TABLE IF EXISTS `classification`;

CREATE TABLE `classification` (
  `CLASS_ID` varchar(36) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  `FATHER_ID` varchar(36) DEFAULT NULL,
  `TIME` varchar(20) NOT NULL,
  `IF_FATHER` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CLASS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `config` */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `CONFIG_ID` varchar(10) NOT NULL,
  `KEY` varchar(20) NOT NULL,
  `VALUE` varchar(36) NOT NULL,
  PRIMARY KEY (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `my_order` */

DROP TABLE IF EXISTS `my_order`;

CREATE TABLE `my_order` (
  `ORDER_ID` varchar(36) NOT NULL,
  `ADDRESS_ID` varchar(36) NOT NULL,
  `BOOK_ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) NOT NULL,
  `PAYED_MONEY` double NOT NULL,
  `PAY_TYPE` varchar(10) NOT NULL,
  `TIME` varchar(20) NOT NULL,
  `STATE` varchar(10) NOT NULL,
  `IS_DELETED` int(1) NOT NULL DEFAULT '0',
  `remark` varchar(100) NOT NULL,
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `publisher` */

DROP TABLE IF EXISTS `publisher`;

CREATE TABLE `publisher` (
  `PUBLISHER_ID` varchar(36) NOT NULL,
  `NAME` varchar(36) NOT NULL,
  PRIMARY KEY (`PUBLISHER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `publisher_book` */

DROP TABLE IF EXISTS `publisher_book`;

CREATE TABLE `publisher_book` (
  `PB_ID` varchar(36) NOT NULL,
  `PUBLISHER_ID` varchar(36) NOT NULL,
  `BOOK_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`PB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `REVIEW_ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) NOT NULL,
  `FATHER_ID` varchar(36) NOT NULL,
  `TIME` varchar(20) NOT NULL,
  `STAR` int(1) NOT NULL,
  `REVIEW_TYPE` varchar(10) NOT NULL DEFAULT 'order',
  `LIKE` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`REVIEW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `review_like` */

DROP TABLE IF EXISTS `review_like`;

CREATE TABLE `review_like` (
  `RL_ID` varchar(36) NOT NULL,
  `REVIEW_ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) NOT NULL,
  `TIME` varchar(20) NOT NULL,
  PRIMARY KEY (`RL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `shopping_cart` */

DROP TABLE IF EXISTS `shopping_cart`;

CREATE TABLE `shopping_cart` (
  `SHOPPING_CART_ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) NOT NULL,
  `BOOK_ID` varchar(36) NOT NULL,
  `NUMBER` int(3) NOT NULL,
  PRIMARY KEY (`SHOPPING_CART_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `USER_ID` varchar(36) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `NICKNAME` varchar(20) NOT NULL,
  `AVATAR` varchar(255) NOT NULL,
  `GENDER` int(11) NOT NULL,
  `TIME` varchar(20) NOT NULL,
  `LONGITUDE` decimal(10,0) NOT NULL,
  `LATITUDE` decimal(10,0) NOT NULL,
  `SESSION_ID` varchar(40) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
