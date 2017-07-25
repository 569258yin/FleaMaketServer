/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.24 : Database - secondhand
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`secondhand` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `secondhand`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `userid` varchar(48) NOT NULL,
  `useraccount` varchar(24) NOT NULL,
  `userpassword` varchar(24) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `useraccount` (`useraccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `account_token` */

DROP TABLE IF EXISTS `account_token`;

CREATE TABLE `account_token` (
  `tokenid` varchar(48) NOT NULL,
  `token` varchar(64) NOT NULL,
  `userid` varchar(48) NOT NULL,
  `createtime` varchar(32) NOT NULL,
  PRIMARY KEY (`tokenid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `foundcase` */

DROP TABLE IF EXISTS `foundcase`;

CREATE TABLE `foundcase` (
  `fdcId` varchar(48) NOT NULL,
  `userId` varchar(48) NOT NULL,
  `fdcType` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '0 丢失  1捡到',
  `fdcTitle` varchar(128) DEFAULT NULL,
  `fdcTime` datetime NOT NULL,
  `fdcContext` varchar(512) NOT NULL,
  `fdcImage` varchar(1000) DEFAULT NULL,
  `modifyTime` datetime NOT NULL,
  PRIMARY KEY (`fdcId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goodsId` varchar(48) NOT NULL,
  `userId` varchar(48) NOT NULL,
  `goodsTypeId` int(3) NOT NULL,
  `goodsTitle` varchar(64) DEFAULT NULL,
  `goodsTime` datetime NOT NULL,
  `goodsText` varchar(512) DEFAULT NULL,
  `goodsMoney` varchar(20) DEFAULT '0',
  `goodSoldmoney` varchar(20) DEFAULT '0',
  `goodSquality` varchar(20) DEFAULT NULL,
  `goodsIconNumber` int(2) DEFAULT '0',
  `goodsIcon` text,
  `userAddress` varchar(64) DEFAULT NULL,
  `userPhone` varchar(11) DEFAULT NULL,
  `coonectTime` varchar(64) DEFAULT NULL,
  `goodsLikeNum` int(8) DEFAULT '0',
  `goodsRepalynum` int(8) DEFAULT '0',
  `modifyTime` datetime NOT NULL,
  PRIMARY KEY (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `goods_search` */

DROP TABLE IF EXISTS `goods_search`;

CREATE TABLE `goods_search` (
  `searchId` varchar(48) NOT NULL,
  `goodsId` varchar(48) NOT NULL,
  `keyWords` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`searchId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `goodscollect` */

DROP TABLE IF EXISTS `goodscollect`;

CREATE TABLE `goodscollect` (
  `gcId` varchar(48) NOT NULL,
  `goodsId` varchar(48) NOT NULL,
  `userId` varchar(48) NOT NULL,
  PRIMARY KEY (`gcId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `goodslike` */

DROP TABLE IF EXISTS `goodslike`;

CREATE TABLE `goodslike` (
  `goodsLikeId` varchar(48) NOT NULL,
  `goodsId` varchar(48) NOT NULL,
  `userId` varchar(48) NOT NULL,
  PRIMARY KEY (`goodsLikeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `goodsrepaly` */

DROP TABLE IF EXISTS `goodsrepaly`;

CREATE TABLE `goodsrepaly` (
  `goodsReplayId` varchar(48) NOT NULL,
  `goodsId` varchar(48) NOT NULL,
  `userId` varchar(48) NOT NULL,
  `goodsReplayTime` datetime NOT NULL,
  `goodsReplayContent` varchar(512) NOT NULL,
  PRIMARY KEY (`goodsReplayId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `goodstypeid` int(3) NOT NULL,
  `goodstypename` varchar(64) NOT NULL,
  `parentid` int(3) DEFAULT '0',
  PRIMARY KEY (`goodstypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `phonecode` */

DROP TABLE IF EXISTS `phonecode`;

CREATE TABLE `phonecode` (
  `id` varchar(48) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `creatTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `searchtag` */

DROP TABLE IF EXISTS `searchtag`;

CREATE TABLE `searchtag` (
  `id` varchar(48) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `countTag` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `torepaly` */

DROP TABLE IF EXISTS `torepaly`;

CREATE TABLE `torepaly` (
  `torepalyId` varchar(48) NOT NULL,
  `goodsReplayId` varchar(48) NOT NULL,
  `userId` varchar(48) NOT NULL COMMENT '评论人',
  `touserId` varchar(48) NOT NULL COMMENT '被评论人',
  `torepalyContext` varchar(512) NOT NULL,
  `torepalyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`torepalyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `userId` varchar(48) NOT NULL,
  `userSex` int(1) DEFAULT '0' COMMENT '0 man 1 woman',
  `userAge` int(3) DEFAULT '0',
  `userAddress` varchar(128) DEFAULT NULL,
  `nickName` varchar(64) DEFAULT NULL,
  `signature` varchar(512) DEFAULT NULL,
  `qqNumber` varchar(20) DEFAULT NULL,
  `wxNumber` varchar(20) DEFAULT NULL,
  `colleage` varchar(64) DEFAULT NULL,
  `school` varchar(64) DEFAULT NULL,
  `userIcon` varchar(1024) DEFAULT NULL,
  `userPhone` varchar(11) DEFAULT NULL,
  `validateNumber` varchar(10) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `modifyTime` datetime NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
