/*
 Navicat Premium Dump SQL

 Source Server         : 111111
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : moonlighting

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 19/12/2024 05:43:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adver
-- ----------------------------
DROP TABLE IF EXISTS `adver`;
CREATE TABLE `adver`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '联关用户表',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '题标',
  `content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '容内',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '片图',
  `position` int NULL DEFAULT 0 COMMENT '告广位置，广告1:1，广告2:2，广告3:3',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `adver_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '广告表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of adver
-- ----------------------------
INSERT INTO `adver` VALUES (5, 10000, '兼职网站', '兼职网站', 'http://localhost:8080/FlexiWork/systemImages/2020010511352183b17.png', 1, '2024-12-15 17:41:30');
INSERT INTO `adver` VALUES (6, 10000, '得力办公', '得力办公', 'http://localhost:8080/FlexiWork/systemImages/202001051135101d248.png', 2, '2024-12-15 17:42:03');
INSERT INTO `adver` VALUES (7, 10000, '人工智能', '人工智能', 'http://localhost:8080/FlexiWork/systemImages/20200105113453a5759.png', 3, '2024-12-15 17:42:21');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '联关用户表',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '题标',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '片图',
  `order_status` int NULL DEFAULT NULL,
  `is_effect` int NULL DEFAULT 1 COMMENT '否是有效，1有效，0无效',
  `create_time` datetime NULL DEFAULT NULL COMMENT '立建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `banner_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '轮播表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, 10000, '11', 'http://localhost:8080/FlexiWork/systemImages/202001051138584ac8d.jpg', 1, 1, '2024-12-15 10:08:42');
INSERT INTO `banner` VALUES (2, 10000, '22', 'http://localhost:8080/FlexiWork/systemImages/202001051139093b0bb.jpg', 2, 1, '2024-12-15 10:08:59');
INSERT INTO `banner` VALUES (3, 10000, '33', 'http://localhost:8080/FlexiWork/systemImages/202001051139203164b.jpg', 3, 1, '2024-12-15 10:09:15');

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id_fa` int NOT NULL COMMENT '送方发',
  `user_id_jie` int NOT NULL COMMENT '接收方',
  `content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '容内',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '片图',
  `create_time` datetime NULL DEFAULT NULL COMMENT '建立时间',
  `is_look` int NOT NULL DEFAULT 0 COMMENT '息消是否已查看，0:未查看，1:已查看',
  `is_remove_fa` int NOT NULL DEFAULT 0 COMMENT '发送方判断是否删除，0:未删除，1:删除',
  `is_remove_jie` int NOT NULL DEFAULT 0 COMMENT '接收方判断是否删除，0:未删除，1:删除',
  `chat_signal` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '聊天信号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_fa`(`user_id_fa` ASC) USING BTREE,
  INDEX `user_id_jie`(`user_id_jie` ASC) USING BTREE,
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`user_id_fa`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `chat_ibfk_2` FOREIGN KEY (`user_id_jie`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '聊天表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (1, 10003, 10000, '请问在吗？？', NULL, '2024-12-15 16:22:21', 1, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (2, 10000, 10003, '在的请问有事吗？？', NULL, '2024-12-15 16:23:32', 1, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (3, 10003, 10000, '明天一起吃个饭呗', NULL, '2024-12-15 16:30:52', 1, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (4, 10000, 10002, '您好', NULL, '2024-12-15 16:32:04', 1, 0, 0, '2019032416320458132');
INSERT INTO `chat` VALUES (5, 10000, 10003, 'okok', NULL, '2024-12-15 16:35:42', 1, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (6, 10000, 10003, '地点哪里？？', NULL, '2024-12-15 16:39:50', 1, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (7, 10003, 10000, '学校食堂吧', NULL, '2024-12-15 16:40:45', 1, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (8, 10003, 10004, '您好！', NULL, '2024-12-08 15:56:38', 1, 0, 0, '201904081556383db98');
INSERT INTO `chat` VALUES (9, 10004, 10003, '你好，很高兴认识你', NULL, '2024-12-08 16:56:46', 1, 0, 0, '201904081556383db98');
INSERT INTO `chat` VALUES (10, 10003, 10004, '今晚约吗？', NULL, '2024-12-08 17:00:28', 1, 0, 0, '201904081556383db98');
INSERT INTO `chat` VALUES (11, 10002, 10000, '你好', NULL, '2024-12-08 13:43:47', 1, 0, 0, '2019032416320458132');
INSERT INTO `chat` VALUES (12, 10002, 10000, '在吗？', NULL, '2024-12-08 20:50:35', 1, 0, 0, '2019032416320458132');
INSERT INTO `chat` VALUES (13, 10003, 10000, '好像三楼有个麻辣香锅还不错！', NULL, '2024-12-08 21:27:17', 1, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (14, 10000, 10002, '你好，在的', NULL, '2024-12-08 16:05:41', 1, 0, 0, '2019032416320458132');
INSERT INTO `chat` VALUES (15, 10000, 10003, '好，那明天就去那里', NULL, '2024-12-08 16:06:26', 1, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (16, 10000, 10002, '哈喽\n', NULL, '2024-12-08 20:21:47', 1, 0, 0, '2019032416320458132');
INSERT INTO `chat` VALUES (17, 10000, 10003, '好的', NULL, '2024-12-08 21:07:35', 0, 0, 0, '20190324162221cd1cd');
INSERT INTO `chat` VALUES (18, 10002, 10003, '', NULL, '2024-12-08 09:35:09', 0, 0, 0, '20230221093509e2f85');
INSERT INTO `chat` VALUES (19, 10002, 10003, '你好\n', NULL, '2024-12-08 21:40:24', 0, 0, 0, '20230221093509e2f85');
INSERT INTO `chat` VALUES (20, 10002, 10003, 'hallo\n\n', NULL, '2024-12-08 16:15:57', 0, 0, 0, '20230221093509e2f85');
INSERT INTO `chat` VALUES (21, 10002, 10000, '你好', NULL, '2024-12-12 16:28:54', 1, 0, 0, '2019032416320458132');
INSERT INTO `chat` VALUES (22, 10002, 10000, '1\n', NULL, '2024-12-12 16:29:24', 1, 0, 0, '2019032416320458132');
INSERT INTO `chat` VALUES (23, 10002, 10000, '1', NULL, '2024-12-12 16:29:33', 1, 0, 0, '2019032416320458132');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '联关用户表',
  `forum_id` int NOT NULL COMMENT '关联帖子表',
  `create_time` datetime NULL DEFAULT NULL COMMENT '立建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`user_id` ASC) USING BTREE,
  INDEX `forum _id`(`forum_id` ASC) USING BTREE,
  CONSTRAINT `collect_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `collect_ibfk_2` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`forum_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '收藏表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (1, 10002, 2, '2024-12-08 16:13:58');
INSERT INTO `collect` VALUES (3, 10002, 5, '2024-12-08 20:50:00');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `user_id_fa` int NOT NULL,
  `user_id_jie` int NOT NULL,
  `file_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `work_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`file_id`) USING BTREE,
  INDEX `user_id_fa`(`user_id_fa` ASC) USING BTREE,
  INDEX `user_id_jie`(`user_id_jie` ASC) USING BTREE,
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`user_id_fa`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `file_ibfk_2` FOREIGN KEY (`user_id_jie`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (5, 10000, 10002, 'G:\\Programming tools\\work\\FlexiWork\\out\\artifacts\\FlexiWork_war_exploded\\web\\resume\\小组时政分享.pdf', '1', '2024-12-12 15:42:13');
INSERT INTO `file` VALUES (6, 10002, 10000, 'G:\\Programming tools\\work\\FlexiWork\\out\\artifacts\\FlexiWork_war_exploded\\web\\resume\\毛概题库学习通整理版（1268题）.pdf', '1', '2024-12-12 16:42:16');

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `forum_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '联关用户表',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '题标',
  `content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '容内',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '片图',
  `is_effect` int NULL DEFAULT 1 COMMENT '否是有效1是0否',
  `create_time` datetime NULL DEFAULT NULL COMMENT '立建时间',
  PRIMARY KEY (`forum_id`) USING BTREE,
  INDEX `u_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `forum_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '帖子表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES (1, 10003, 'Java编程规范个人总结', NULL, '', 0, '2024-12-08 16:04:02');
INSERT INTO `forum` VALUES (2, 10003, 'Java编程规范个人总结', '命名规约：1、所有命名不能以美元符和下划线开始和结尾；2、禁止拼音加英文类，UserDao..方法，变量，参数用userDao；3、常量名全部大写，下划线隔开；4抽象类使用Abstract或者Base开头，异常类使用Exception结尾；5、boolean属性命名不能使用is开头，如果使用到了设计模式，建议在类名中体现出具体模式；6、LoginProxy接口类中方法不加权限修饰符，接口和实现类 实现类以Impl结尾。7、MVC各层命名规约：（1）Service/Dao层：获取单个对象 get 前缀，获取多个对象 list 前缀，获取统计值的方法 count 前缀，插入 save 前缀，删除 remove，修改 update；（2）领域模型：数据对象 DO 是数据表名，数据传输对象 DTO 是业务领域相关的名称，展示对象 VO 是网页名称 使用了模板框架，POJO是 DO DTO BO VO 统称。', '', 1, '2024-12-08 16:11:20');
INSERT INTO `forum` VALUES (3, 10002, '给大家推荐本书', '下面这本java ee企业级应用开发教程，出于黑马程序员，此书的内容编写的十分紧凑、简洁而不简单，里边的案例讲解的知识点很清晰，很人性化非常适合学习，推荐给正在学java ee的小伙伴。', 'http://localhost:8080/FlexiWork/forumImages/202001051131507775b.jpg', 1, '2024-12-08 16:13:37');
INSERT INTO `forum` VALUES (4, 10003, '王者荣耀孙悟空排位出装', '关于猴子的出装和铭文搭配：猴子现在入场一般是打野位，也有打上单的，但是上单位比较难打。铭文的话，我个人推荐铭文10个夺萃10个鹰眼10个无双。这样配可以打上单和打野，有暴击有吸血续航能力比较强。出装推荐，一把追击刀锋打野刀+疾步之靴+无尽战刃+泣血之刃+宗师之力+名刀司命，这样出装猴子用技能可以打出3600多暴击,平A可以打出1800多暴击，到了后期可以把打野刀名刀和靴换掉。喜欢猴子的可以参考以上出装哦。', 'http://localhost:8080/FlexiWork/forumImages/2020010511312343595.jpg', 1, '2024-12-08 16:16:02');
INSERT INTO `forum` VALUES (5, 10003, '关于Web前端开发', '前端开发是创建Web页面或app等前端界面呈现给用户的过程。前端开发通过HTML，CSS及JavaScript以及衍生出来的各种技术、框架、解决方案，来实现互联网产品的用户界面交互 。它从网页制作演变而来，名称上有很明显的时代特征。在互联网的演化进程中，网页制作是Web1.0时代的产物，早期网站主要内容都是静态，以图片和文字为主，用户使用网站的行为也以浏览为主。随着互联网技术的发展和HTML5、CSS3的应用，现代网页更加美观，交互效果显著，功能更加强大。关于想学习web前端开发的小伙伴，个人给出一点学习建议：第一阶段学习html5+css3+ps,会熟练的应用ps可以让你的网页加分不少。第二阶段：js+jq+bootstrap, 学完第二阶段已经可以做些比较炫酷网页了，第三阶段：vue+react+ Angular等框架，对于要走web前端开发的小伙伴，第三阶段的知识必不可少哦。', '', 1, '2024-12-08 16:19:47');
INSERT INTO `forum` VALUES (6, 10000, '心情贴', '今天是周日，来学校的第三周结束了，兼职平台也完工了，这些天总感觉心情有点复杂！', '', 1, '2024-12-08 22:37:44');
INSERT INTO `forum` VALUES (7, 10002, '我的大魅族', '在下一直是魅族的小粉丝，这次免费帮魅族打一个广告，哈哈', 'http://localhost:8080/FlexiWork/forumImages/2020010511305959af2.png', 1, '2024-12-08 16:59:50');
INSERT INTO `forum` VALUES (8, 10002, '本田雅阁', '', 'http://localhost:8080/FlexiWork/forumImages/202001051130432c162.jpg', 1, '2024-12-08 17:04:46');
INSERT INTO `forum` VALUES (9, 10004, '清明假期', '今天是清明放假的最后一天了，好好过，加油！', '', 1, '2024-12-08 15:12:47');
INSERT INTO `forum` VALUES (10, 10004, '一张好看的图片送给大家', '', 'http://localhost:8080/FlexiWork/forumImages/20200105113016ffe04.png', 1, '2024-12-08 15:13:58');
INSERT INTO `forum` VALUES (11, 10003, '毕业将至', '距离答辩越来越近了，每当走在校园的每个角落，总感慨这四年过得太快了，我的大学生活所剩无几！', '', 1, '2024-12-08 16:26:41');
INSERT INTO `forum` VALUES (12, 10003, '生活就该这样', '生活吧，其实每个人都有自己的难处，但是只要我们不向困难低头，一切都会好起来的！', 'http://localhost:8080/FlexiWork/forumImages/20200105112948a49fa.jpg', 1, '2024-12-08 16:37:04');
INSERT INTO `forum` VALUES (13, 10002, '星空壁纸', '', 'http://localhost:8080/FlexiWork/forumImages/2020010511293525e1d.jpg', 1, '2024-12-08 16:53:01');
INSERT INTO `forum` VALUES (14, 10000, '111', '111', '', 0, '2024-12-08 10:49:21');
INSERT INTO `forum` VALUES (15, 10000, '测试', '', 'http://localhost:8080/FlexiWork/forumImages/20200105110102f27be.jpg', 0, '2024-12-08 11:01:05');
INSERT INTO `forum` VALUES (16, 10002, '南京长江大桥我来了！', '呜呜┭┮﹏┭┮', 'http://localhost:8080/FlexiWork/forumImages/20241124215511424c1.jpg', 1, '2024-12-08 21:55:13');

-- ----------------------------
-- Table structure for forum_reply
-- ----------------------------
DROP TABLE IF EXISTS `forum_reply`;
CREATE TABLE `forum_reply`  (
  `reply_id` int NOT NULL AUTO_INCREMENT,
  `forum_id` int NOT NULL COMMENT '联关帖子表',
  `user_id` int NOT NULL COMMENT '联关用户表',
  `reply_content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '复回内容',
  `reply_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '复回图片',
  `reply_time` datetime NULL DEFAULT NULL COMMENT '复回时间',
  `status` int NOT NULL DEFAULT 0 COMMENT '回复状态0未查看1已查看',
  PRIMARY KEY (`reply_id`) USING BTREE,
  INDEX `forum _id`(`forum_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `forum_reply_ibfk_2` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`forum_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `forum_reply_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '帖子回复表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of forum_reply
-- ----------------------------
INSERT INTO `forum_reply` VALUES (1, 3, 10003, '这本书我看过，感觉挺不错的，适合入门的同学', NULL, '2024-12-08 16:16:59', 1);
INSERT INTO `forum_reply` VALUES (2, 4, 10003, '皮肤挺不错，谁送我一个？', NULL, '2024-12-08 16:17:46', 1);
INSERT INTO `forum_reply` VALUES (3, 8, 10004, '这辆车挺好看的，运动感很强，适合年轻人的审美，你是否也想要一辆呢？', NULL, '2024-12-08 15:22:22', 1);
INSERT INTO `forum_reply` VALUES (4, 3, 10003, '恩恩，楼上说的对，我也是这么觉得的', NULL, '2024-12-08 16:28:08', 0);
INSERT INTO `forum_reply` VALUES (5, 13, 10000, '12', NULL, '2024-12-08 21:07:47', 1);

-- ----------------------------
-- Table structure for jianzhi
-- ----------------------------
DROP TABLE IF EXISTS `jianzhi`;
CREATE TABLE `jianzhi`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `work_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jianzhi
-- ----------------------------
INSERT INTO `jianzhi` VALUES (5, 11, 10000);
INSERT INTO `jianzhi` VALUES (6, 10, 10000);
INSERT INTO `jianzhi` VALUES (7, 8, 10000);
INSERT INTO `jianzhi` VALUES (8, 16, 10002);
INSERT INTO `jianzhi` VALUES (9, 4, 10002);
INSERT INTO `jianzhi` VALUES (10, 4, 10002);

-- ----------------------------
-- Table structure for mailbox
-- ----------------------------
DROP TABLE IF EXISTS `mailbox`;
CREATE TABLE `mailbox`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '题主',
  `content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '容内',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片',
  `create_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `mailbox_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '信箱表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mailbox
-- ----------------------------
INSERT INTO `mailbox` VALUES (1, 10000, '关于论坛模块', '希望平台可以优化', '', '2024-12-08 16:25:03');
INSERT INTO `mailbox` VALUES (2, 10000, '体验', '还有一些关于体验性的东西，亦可以优化', 'http://localhost:8080/FlexiWork/mailboxImages/202001051136418ce6d.png', '2024-12-08 16:26:12');
INSERT INTO `mailbox` VALUES (3, 10003, '提个建议', '希望平台对虚假信息严格禁止', 'http://localhost:8080/FlexiWork/mailboxImages/20200105113551c7ee1.png', '2024-12-08 16:21:31');
INSERT INTO `mailbox` VALUES (4, 10000, '是的是的', '哈哈', 'http://localhost:8080/FlexiWork/mailboxImages/20230219202159f0b75.png', '2024-12-08 20:22:04');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '户表用',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '题标',
  `content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '容内',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '片图',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '公告表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 10000, '兼职平台1.0版本上线了！', '兼职平台1.0版本上线了，请大家尽情体验吧！在体验的过程中碰到什么问题，可以通过平台信箱告知平台哦，祝大家生活愉快！', '', '2024-12-08 16:47:36');
INSERT INTO `notice` VALUES (2, 10000, '12', '12', '', '2024-12-08 21:09:12');
INSERT INTO `notice` VALUES (3, 10003, '提个建议', '希望平台对虚假信息严格禁止', 'http://localhost:8080/FlexiWork/mailboxImages/20200105113551c7ee1.png', '2024-12-08 16:21:31');
INSERT INTO `notice` VALUES (4, 10000, '是的是的', '哈哈', 'http://localhost:8080/FlexiWork/mailboxImages/20230219202159f0b75.png', '2024-12-08 20:22:04');

-- ----------------------------
-- Table structure for system_upset
-- ----------------------------
DROP TABLE IF EXISTS `system_upset`;
CREATE TABLE `system_upset`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '关联用户表',
  `welcom_text` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '欢迎语',
  `company_addres` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'å€åœ°',
  `company` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '司公',
  `qq` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'qq',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
  `company_logo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'logo',
  `we_chat` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '信微',
  `create_time` datetime NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `system_upset_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of system_upset
-- ----------------------------
INSERT INTO `system_upset` VALUES (3, 10000, '欢迎来到FlexiWork恐龙校园兼职平台', '成都理工大学', '管你对不队', '1273232532', '13658166051', 'http://localhost:8080/FlexiWork/systemImages/main_01.png', '小可爱', '2024-12-15 17:26:51');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '称昵',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '码密',
  `user_leven` int NULL DEFAULT 0 COMMENT '户用等级',
  `user_category` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '普通用户类别',
  `sex` int NULL DEFAULT 0 COMMENT '别性0女1男',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '话电',
  `pirthday` datetime NULL DEFAULT NULL COMMENT '日生',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '箱邮',
  `header_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '像头',
  `question` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '回找密码问题',
  `answer` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '回找密码答案',
  `create_time` datetime NULL DEFAULT NULL COMMENT '立建时间',
  `is_effect` int NULL DEFAULT 1 COMMENT '否是有效，1有效，0无效',
  `is_work` int NULL DEFAULT 1 COMMENT '0不可以发布1可以发布',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '注备',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10005 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10000, '王先生', '小辉', 'e10adc3949ba59abbe56e057f20f883e', 1, '管理员', 1, '17807705665', NULL, '1395302251@qq.com', 'http://localhost:8080/FlexiWork/headerImages/2020010511220709699.jpg', NULL, NULL, '2024-12-08 17:16:07', 1, 1, '单位：右江公司，地址：百色右江区，电话：17807705665，qq：1395302251');
INSERT INTO `user` VALUES (10001, '饶先生', '小黄', 'e10adc3949ba59abbe56e057f20f883e', 1, '管理员', 1, '17807705665', NULL, '1395302251@qq.com', 'http://localhost:8080/FlexiWork/headerImages/202001051121571b236.jpg', NULL, NULL, '2024-12-08 10:32:59', 1, 1, NULL);
INSERT INTO `user` VALUES (10002, '高先生', '小可爱哇', 'e10adc3949ba59abbe56e057f20f883e', 0, '学生', 1, '13648055159', NULL, '1273232532@qq.com', 'http://localhost:8080/FlexiWork/headerImages/20241203004557d6ff4.jpg', NULL, NULL, '2024-12-08 10:46:23', 1, 1, '可以发布兼职');
INSERT INTO `user` VALUES (10003, '舒先生', '小陈88', 'e10adc3949ba59abbe56e057f20f883e', 0, '学生', 1, '17807705665', NULL, '1529117748@qq.com', 'http://localhost:8080/FlexiWork/headerImages/20200105112128eaba6.jpg', '我的妈妈叫啥名字？', '小秀秀', '2024-12-08 10:48:24', 1, 1, '单位：右江公司，地址：右江区，电话：17807705665，qq：1395302251');
INSERT INTO `user` VALUES (10004, '廖先生', '小黄88', 'e10adc3949ba59abbe56e057f20f883e', 0, '学生', 1, '17807705665', NULL, '123456@qq.com', 'http://localhost:8080/FlexiWork/headerImages/2020010511204514401.jpg', '我是谁呢？', '大地', '2024-12-08 11:06:34', 1, 1, NULL);

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `work_category` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '职兼类别,促销、传单派发、设计、家教、会计、计算机、服务员、其他',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '话电',
  `content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '容内',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '片图',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '题标',
  `is_auditing` int NOT NULL DEFAULT 0 COMMENT '否是审核0否1是',
  `is_effect` int NOT NULL DEFAULT 1 COMMENT '否是有效，1有效，0无效',
  `create_time` datetime NULL DEFAULT NULL COMMENT '立建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `work_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES (1, 10002, '传单派发', '17807705665', '消息：右江区盼盼食品公司招聘传单派发兼职，时间：4月19号上午9点，地点：右江区中山一路，招聘人数10人，工作八小时薪酬80元，可通过线上联系和电话联系，先到先得，谢谢！', '', '传单派发兼职', 1, 1, '2024-12-08 11:03:04');
INSERT INTO `work` VALUES (2, 10000, '会计', '17807705660', '消息：广西百色皮皮商务公司招聘会计兼职数名，薪酬面议，不限男女，工作时间弹性安排，地点：东合大桥东，需要面试的人员请提前一天线上联系（说明：本帖子长期有效，学生表现优秀，毕业后可以转正）。', '', '招聘会计兼职', 1, 1, '2024-12-08 11:10:42');
INSERT INTO `work` VALUES (3, 10000, '计算机', '17807705664', '消息：百色蓝光科技有限公司招聘前端工程师兼职数名，薪酬面议，地址：百色火车站附近，有意向的小伙伴请近期电话联系，谢谢！', '', '招聘计算机兼职', 1, 1, '2024-12-08 11:16:46');
INSERT INTO `work` VALUES (4, 10002, '促销', '13471714079', '消息：百色市拼多多超市招聘促销兼职2名，要求女生，口才好优先，薪酬：8元1小时，有提成。时间：3月30号周六，有意向的同学请提前联系，谢谢！', '', '召促销兼职', 1, 1, '2024-12-08 16:35:39');
INSERT INTO `work` VALUES (5, 10002, '服务员', '13471714079', '急招兼职：百色市右江区某某饭店招聘两名服务员，时间：下午六点，薪酬：50元，工作3小时左右，地点：中山二路15号。有意向的同学尽快联系！', '', '招聘服务员兼职', 1, 1, '2024-12-08 10:13:00');
INSERT INTO `work` VALUES (6, 10004, '传单派发', '17807705665', '招会计兼职', 'http://localhost:8080/FlexiWork/workImages/20200105112423441ed.jpg', '招会计兼职', 1, 0, '2024-12-08 14:59:16');
INSERT INTO `work` VALUES (7, 10002, '会计', '17807705668', '招兼职', '', '招会计兼职！！', 1, 0, '2024-12-08 23:14:24');
INSERT INTO `work` VALUES (8, 10003, '服务员', '17807705665', '急招兼职：百色市右江区某某饭店招聘两名服务员，时间：下午六点，薪酬：50元，工作3小时左右，地点：中山二路15号。有意向的同学尽快联系！', 'http://localhost:8080/FlexiWork/workImages/20200105112440ee631.jpg', '招聘服务员兼职', 1, 1, '2024-12-08 15:57:32');
INSERT INTO `work` VALUES (9, 10003, '促销', '17807705665', '消息：百色市华润超市招聘促销兼职2名，要求女生，口才好优先，薪酬：8元1小时，有提成。时间：6月30号周六，有意向的同学请提前联系，谢谢！', '', '华润超市促销兼职', 1, 1, '2024-12-08 15:59:51');
INSERT INTO `work` VALUES (10, 10003, '计算机', '17807705665', '消息：百色右江科技有限公司招聘前端工程师兼职数名，薪酬面议，地址：百色火车站附近，有意向的小伙伴请近期电话联系，谢谢！', '', '右江科技', 1, 1, '2024-12-08 16:01:49');
INSERT INTO `work` VALUES (11, 10000, '会计', '17807705665', '消息：广西百色中心商务公司招聘会计兼职数名，薪酬面议，不限男女，工作时间弹性安排，地点：东合大桥东，需要面试的人员请提前一天线上联系（说明：本帖子长期有效，学生表现优秀，毕业后可以转正）。', '', '中心商务公司', 1, 1, '2024-12-08 16:16:22');
INSERT INTO `work` VALUES (12, 10003, '传单派发', '17807705665', 'eeee', '', 'eeee', 0, 0, '2024-12-08 10:34:14');
INSERT INTO `work` VALUES (13, 10003, '计算机', '17807705665', 'gggg', '', '招聘计算机兼职', 0, 1, '2024-12-08 14:02:14');
INSERT INTO `work` VALUES (14, 10002, '设计', '15161139838', '12', '', '12', 0, 1, '2024-12-08 09:40:23');
INSERT INTO `work` VALUES (15, 10000, '传单派发', '15132222222', '12', '', '12', 0, 0, '2024-12-08 23:12:06');
INSERT INTO `work` VALUES (16, 10002, '其他', '13648055159', '每晚12:00-凌晨3:00，在龙潭寺霸王茶姬店进行店铺卫生打扫（包括工具维护、产桌摆放等等），详情请私聊', 'http://localhost:8080/FlexiWork/workImages/20241124215221285ca.jpg', '霸王茶姬打烊兼职', 1, 1, '2024-12-08 21:52:25');

SET FOREIGN_KEY_CHECKS = 1;
