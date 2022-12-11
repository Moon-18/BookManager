/*
 Navicat Premium Data Transfer

 Source Server         : 如月十八
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : springcloud

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 11/12/2022 21:35:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `ID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `WRITER` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `WORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键词',
  `KIND` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书种类，科教文卫体与其他',
  `COMMENT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书描述',
  `CUR_NUM` int(0) NOT NULL COMMENT '当前在馆数量',
  `SUM_NUM` int(0) NOT NULL COMMENT '图书全部数量',
  `gmt_create` datetime(0) NOT NULL COMMENT '入馆日期',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `NAME`(`NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1489485982758727681', '红楼梦', '[清] 曹雪芹', '古文', '文化', '都云作者痴，谁解其中味？', 19, 20, '2022-02-01 00:00:00', '2022-07-29 10:41:22');
INSERT INTO `book` VALUES ('1489486411462606850', '活着', '余华', '现代', '文化', '生的苦难与伟大', 15, 15, '2022-02-02 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1489486579633254401', '1984', '[英] 乔治·奥威尔 刘绍铭\n', '外国经典', '文化', '栗树荫下，我出卖你，你出卖我\n', 10, 10, '2022-02-03 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1489486901239980033', '百年孤独', '[哥伦比亚] 加西亚·马尔克斯', '外国经典', '文化', '魔幻现实主义文学代表作\n', 5, 5, '2022-02-04 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1489488782225956865', '飘', '[美国] 玛格丽特·米切尔', '外国经典', '文化', '革命时期的爱情，随风而逝\n', 3, 3, '2022-02-05 00:00:00', '2022-07-29 10:48:16');
INSERT INTO `book` VALUES ('1489488928158273537', '三体', '刘慈欣\n', '科幻著作', '文化', '地球往事三部曲\n', 7, 8, '2022-02-05 00:00:00', '2022-08-22 11:36:33');
INSERT INTO `book` VALUES ('1489489151349862402', '三国演义', '[明] 罗贯中\n', '历史风云', '文化', '是非成败转头空\n', 30, 30, '2022-02-06 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1489489239165915137', '动物农场', '[英] 乔治·奥威尔荣如德\n', '外国', '文化', '太阳底下并无新事\n', 10, 10, '2022-02-07 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1489489328064294913', '房思琪的初恋乐园', '林奕含\n', '情感家园', '文化', '向死而生的文学绝唱\n', 15, 15, '2022-02-08 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1489489688744960002', '人类简史', '[以色列] 尤瓦尔·赫拉利', '历史', '卫生', '跟着人类一同走过十万年\n', 22, 22, '2022-02-09 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1489489818785255426', '中国历代政治得失', '钱穆\n', '政治', '文化', '一部简明的“中国政治制度史”\n', 12, 12, '2022-02-10 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1504368338653011969', '算法竞赛入门经典', '刘汝佳', '计算机专业', '教育', '程序设计思维与实践', 15, 15, '2022-03-17 00:00:00', '2022-06-26 10:37:47');
INSERT INTO `book` VALUES ('1551578133142982658', 'test', 'test', NULL, '卫生', '1', 5, 5, '2022-07-25 22:40:29', '2022-07-28 19:15:51');
INSERT INTO `book` VALUES ('1551578305847644162', '科技', '科技', NULL, '科技', '科技', 5, 5, '2022-07-25 22:41:10', '2022-07-29 09:11:09');
INSERT INTO `book` VALUES ('1551888913801428993', '体育', '体育', NULL, '体育', '体育', 0, 0, '2022-07-26 19:15:24', '2022-07-26 19:15:24');
INSERT INTO `book` VALUES ('1561696747619729410', 'string', 'string', NULL, 'string', 'string', 0, 0, '2022-08-22 20:48:14', '2022-08-22 20:48:14');
INSERT INTO `book` VALUES ('1561697247194890241', '2022年8', '123', NULL, '科技', '2022年8月22日20:50:11', 1, 1, '2022-08-22 20:50:13', '2022-08-22 20:50:13');
INSERT INTO `book` VALUES ('1601931833572945921', '12313', '123', NULL, '科技', '', 10, 10, '2022-12-11 21:28:06', '2022-12-11 21:28:06');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `ID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `BOOK_NAME` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应图书NAME',
  `READER_ACCOUNT` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应读者ACCOUNT',
  `STATE` tinyint(1) NULL DEFAULT NULL COMMENT '0这条记录未结束,1已结束',
  `MESSAGE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime(0) NOT NULL COMMENT '借出时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '实际归还时间',
  `deadline` datetime(0) NOT NULL COMMENT '应该归还时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借阅信息管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('1552846713587245058', '红楼梦', '1911834200@qq.com', 0, NULL, '2022-07-29 10:41:22', '2022-07-29 10:41:22', '2022-09-27 10:41:22');
INSERT INTO `borrow` VALUES ('1552848341820252161', '飘', '1911834200@qq.com', 1, '正常,按时归还', '2022-07-28 10:47:50', '2022-07-29 10:48:16', '2022-09-27 10:47:50');
INSERT INTO `borrow` VALUES ('1561557914492080129', '三体', 'string@qq.com', 0, NULL, '2022-08-22 11:36:34', '2022-08-22 11:36:34', '2022-09-21 11:36:33');

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路径权限',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role` VALUES ('1', '/user/getAll', 'admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `ACCOUNT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `TYPE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型,用户,管理员',
  `PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `allow` tinyint(1) NULL DEFAULT NULL COMMENT '是否允许借阅',
  `max_num` int(0) NULL DEFAULT NULL COMMENT '最大借阅数量',
  `max_time` int(0) NULL DEFAULT NULL COMMENT '最长借阅时间',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ACCOUNT_UNIQUE`(`ACCOUNT`) USING BTREE,
  UNIQUE INDEX `NAME_UNIQUE`(`NAME`) USING BTREE COMMENT '姓名不重复?emmmm先暂且这样设吧'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图书馆的管理员列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1489590815759024129', '张三', 'user1@sina.com', 'user', '123456', NULL, 0, NULL, 2, 30, NULL, '2022-07-28 19:31:18');
INSERT INTO `user` VALUES ('1489591032785006593', '李四', 'user2@sina.com', 'user', '123456', NULL, 0, NULL, 1, 30, NULL, '2022-07-28 10:15:06');
INSERT INTO `user` VALUES ('1489591128557740033', '王五', 'user3@sina.com', 'user', '123456', NULL, 0, NULL, 1, 30, NULL, '2022-07-28 11:24:40');
INSERT INTO `user` VALUES ('1489591182639042561', '赵六', 'user4@sina.com', 'user', '123456', NULL, 0, NULL, 2, 30, NULL, NULL);
INSERT INTO `user` VALUES ('1489591545182081026', '管理员', '1911834200@qq.com', 'admin', '123456', NULL, 0, NULL, 3, 60, NULL, '2022-07-29 10:48:16');
INSERT INTO `user` VALUES ('1561698627074723841', 'string222', '2022年8月22日20:54:01', 'string', 'b45cffe084dd3d20d928bee85e7b0f21', NULL, NULL, NULL, NULL, NULL, '2022-08-22 20:55:42', '2022-08-22 20:55:42');
INSERT INTO `user` VALUES ('1561700815343783938', '123123123', '123@qq.com', 'user', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, NULL, '2022-08-22 21:04:24', '2022-08-22 21:04:24');
INSERT INTO `user` VALUES ('1561701085465350146', '测试', 'ceshi@qq.com', 'user', '4297f44b13955235245b2497399d7a93', NULL, NULL, NULL, NULL, NULL, '2022-08-22 21:05:28', '2022-08-22 21:05:28');
INSERT INTO `user` VALUES ('string', 'string', 'string@qq.com', 'user', '123456', 'string', 1, 1, 1, 30, '2022-07-06 16:12:31', '2022-08-22 11:36:33');

SET FOREIGN_KEY_CHECKS = 1;
