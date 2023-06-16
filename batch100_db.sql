/*
Navicat MySQL Data Transfer

Source Server         : LOCAL
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : batch100_db

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2023-06-17 04:49:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `asignup_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `asignup_tbl`;
CREATE TABLE `asignup_tbl` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `gender` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of asignup_tbl
-- ----------------------------
INSERT INTO `asignup_tbl` VALUES ('7', 'amisha', 'amisha@gmail.com', 'Female');
INSERT INTO `asignup_tbl` VALUES ('12', 'technohunk100@gmail.com', 'nagendra.yadav.niit@gmail.com', 'Male');
INSERT INTO `asignup_tbl` VALUES ('13', 'Nishan', 'nishan@gmail.com', 'Male');
INSERT INTO `asignup_tbl` VALUES ('14', 'Nisha', 'nagendra.yadav.niit@gmail.com', 'Female');
INSERT INTO `asignup_tbl` VALUES ('15', 'Pushpit', 'pushpit@gmail.com', 'Male');
INSERT INTO `asignup_tbl` VALUES ('20', 'javahunk100@gmail.com', 'technohunk444@gmail.com', 'Male');

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('23');
INSERT INTO `hibernate_sequence` VALUES ('23');

-- ----------------------------
-- Table structure for `passport_details_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `passport_details_tbl`;
CREATE TABLE `passport_details_tbl` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `doe` datetime(6) DEFAULT NULL,
  `exp` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `fsid` int(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cpbo03qbshx6emp1xl2nxr9dk` (`fsid`),
  CONSTRAINT `FK8llfdwep7gtl8mrg2tjeikvj9` FOREIGN KEY (`fsid`) REFERENCES `asignup_tbl` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of passport_details_tbl
-- ----------------------------
INSERT INTO `passport_details_tbl` VALUES ('19', 'H01292', '2023-06-16 05:58:00.471000', '2029-09-12 00:00:00.000000', 'amisha', 'ABUP01982882', '7', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqOPrj4cdjZ_RM1j_emNz-TXyTRKKV8-7MRw&usqp=CAU');
INSERT INTO `passport_details_tbl` VALUES ('20', 'Rajnagar delhi', '2023-06-16 05:58:20.707000', '2032-12-12 00:00:00.000000', 'Pushpit', 'ABUP019282882', '15', 'https://cdn-icons-png.flaticon.com/512/3135/3135715.png');
INSERT INTO `passport_details_tbl` VALUES ('21', 'Nepal', '2023-06-16 05:58:36.424000', '2032-12-12 00:00:00.000000', 'Nishan', 'ABTY230303', '13', 'https://cdn-icons-png.flaticon.com/512/3135/3135715.png');

-- ----------------------------
-- Table structure for `psignup_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `psignup_tbl`;
CREATE TABLE `psignup_tbl` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `mobile` int(12) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `doe` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of psignup_tbl
-- ----------------------------
INSERT INTO `psignup_tbl` VALUES ('3', 'Jaya Singh', '12321', 'jayasingh@gmail.com', '82383838', '!22-154,SEC-23, SANJAY NAGAR', '2023-05-10 02:19:31');
INSERT INTO `psignup_tbl` VALUES ('8', 'admin', 'test', 'nagendra.yadav.niit@gmail.com', '987300370', 'H-154 ,SEC-23\r\nSANJAY NAGAR', '2023-05-16 03:17:08');
INSERT INTO `psignup_tbl` VALUES ('9', 'nagendra', 'test', 'nagendra.yadav.niit@gmail.com', '21321213', 'H-154 ,SEC-23\r\nSANJAY NAGAR', '2023-05-17 03:25:56');

-- ----------------------------
-- Table structure for `signups_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `signups_tbl`;
CREATE TABLE `signups_tbl` (
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of signups_tbl
-- ----------------------------
INSERT INTO `signups_tbl` VALUES ('javahunk100@gmail.com', 'kill22', 'samip22@maaticit.com', 'Male');
INSERT INTO `signups_tbl` VALUES ('1231231320@gmail.com', '213123', 'nagendra@cubicit.net', 'Male');
