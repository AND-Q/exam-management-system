/*
 Navicat Premium Data Transfer

 Source Server         : jdbc
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : ems

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 27/01/2025 18:33:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for academy
-- ----------------------------
DROP TABLE IF EXISTS `academy`;
CREATE TABLE `academy`  (
  `id` int NOT NULL,
  `academy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`academy` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of academy
-- ----------------------------
INSERT INTO `academy` VALUES (2, '外国语学院');
INSERT INTO `academy` VALUES (3, '数学与统计学院');
INSERT INTO `academy` VALUES (1, '计算机科学与技术学院');

-- ----------------------------
-- Table structure for einfo
-- ----------------------------
DROP TABLE IF EXISTS `einfo`;
CREATE TABLE `einfo`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '监考科目',
  `teacher` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '监考教师',
  `position` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '考试地点',
  `time` datetime NULL DEFAULT NULL COMMENT '考试时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 145 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of einfo
-- ----------------------------
INSERT INTO `einfo` VALUES (1, '机器学习', 'hwr', '九教206', '2024-12-02 16:00:00');
INSERT INTO `einfo` VALUES (2, '计算机网络', 'hwr', '九教210', '2024-12-04 12:16:59');
INSERT INTO `einfo` VALUES (3, '高等数学', 'hwr,qzy', '九教210', '2024-12-04 12:17:03');
INSERT INTO `einfo` VALUES (4, '数据库系统概论1', 'liushushu, yang1', '三教102', '2024-12-26 12:17:06');
INSERT INTO `einfo` VALUES (142, 'yuw', 'hwr', '211', '2024-12-16 12:46:55');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `infoid` int NULL DEFAULT NULL,
  `uid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_infoid`(`infoid` ASC) USING BTREE,
  INDEX `fk_uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1, 1, 1);
INSERT INTO `exam` VALUES (2, 1, 2);
INSERT INTO `exam` VALUES (3, 2, 1);
INSERT INTO `exam` VALUES (4, 2, 2);
INSERT INTO `exam` VALUES (5, 4, 3);
INSERT INTO `exam` VALUES (6, 4, 6);
INSERT INTO `exam` VALUES (7, 3, 4);

-- ----------------------------
-- Table structure for examlog
-- ----------------------------
DROP TABLE IF EXISTS `examlog`;
CREATE TABLE `examlog`  (
  `LogID` int NOT NULL AUTO_INCREMENT,
  `OperationType` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `InfoID` int NULL DEFAULT NULL,
  `OperationTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `Operator` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `TeacherID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`LogID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examlog
-- ----------------------------
INSERT INTO `examlog` VALUES (1, 'UPDATE', 138, '2024-12-16 00:47:04', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (2, 'INSERT', 139, '2024-12-16 01:01:33', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (3, 'UPDATE', 138, '2024-12-16 01:04:06', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (4, 'DELETE', 138, '2024-12-16 01:04:17', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (5, 'INSERT', 140, '2024-12-16 01:07:56', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (6, 'UPDATE', 137, '2024-12-16 01:08:20', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (7, 'DELETE', 6, '2024-12-16 01:08:38', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (8, 'DELETE', 13, '2024-12-16 01:08:38', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (9, 'UPDATE', 1, '2024-12-16 01:08:54', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (10, 'UPDATE', 3, '2024-12-16 01:08:55', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (11, 'UPDATE', 4, '2024-12-16 01:08:55', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (12, 'UPDATE', 5, '2024-12-16 01:08:56', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (13, 'UPDATE', 1, '2024-12-16 01:09:11', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (14, 'UPDATE', 1, '2024-12-16 01:09:16', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (15, 'UPDATE', 3, '2024-12-16 01:09:19', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (16, 'UPDATE', 5, '2024-12-16 01:09:23', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (25, 'UPDATE', 14, '2024-12-16 01:26:46', 'root@localhost', 'hwr, Qzy');
INSERT INTO `examlog` VALUES (26, 'UPDATE', 137, '2024-12-16 01:27:05', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (27, 'UPDATE', 14, '2024-12-16 01:27:26', 'root@localhost', 'hwr, Qzy');
INSERT INTO `examlog` VALUES (28, 'UPDATE', 137, '2024-12-16 01:27:38', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (29, 'INSERT', 141, '2024-12-16 01:28:10', 'root@localhost', 'liushushu');
INSERT INTO `examlog` VALUES (30, 'UPDATE', 136, '2024-12-16 01:29:08', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (31, 'UPDATE', 139, '2024-12-16 01:29:10', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (32, 'UPDATE', 140, '2024-12-16 01:29:11', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (33, 'UPDATE', 5, '2024-12-16 01:29:13', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (34, 'UPDATE', 4, '2024-12-16 01:29:15', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (35, 'UPDATE', 3, '2024-12-16 01:29:17', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (36, 'UPDATE', 1, '2024-12-16 01:29:19', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (37, 'UPDATE', 14, '2024-12-16 01:29:22', 'root@localhost', 'hwr, qzy');
INSERT INTO `examlog` VALUES (38, 'UPDATE', 137, '2024-12-16 01:29:36', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (39, 'UPDATE', 139, '2024-12-16 01:29:37', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (40, 'UPDATE', 6, '2024-12-16 01:30:00', 'root@localhost', 'hwr, qzy');
INSERT INTO `examlog` VALUES (41, 'UPDATE', 2, '2024-12-16 01:30:05', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (42, 'UPDATE', 3, '2024-12-16 01:30:08', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (43, 'UPDATE', 4, '2024-12-16 01:30:11', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (44, 'UPDATE', 5, '2024-12-16 01:30:14', 'root@localhost', 'hwr, qzy');
INSERT INTO `examlog` VALUES (45, 'UPDATE', 6, '2024-12-16 01:30:17', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (46, 'UPDATE', 7, '2024-12-16 01:30:19', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (47, 'UPDATE', 8, '2024-12-16 01:30:21', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (48, 'UPDATE', 9, '2024-12-16 01:30:24', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (49, 'UPDATE', 10, '2024-12-16 01:30:26', 'root@localhost', 'liushushu');
INSERT INTO `examlog` VALUES (50, 'UPDATE', 1, '2024-12-16 01:30:34', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (51, 'UPDATE', 2, '2024-12-16 01:30:46', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (52, 'DELETE', 5, '2024-12-16 01:30:58', 'root@localhost', 'hwr, qzy');
INSERT INTO `examlog` VALUES (53, 'DELETE', 6, '2024-12-16 01:30:58', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (54, 'DELETE', 7, '2024-12-16 01:30:58', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (55, 'DELETE', 8, '2024-12-16 01:30:58', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (56, 'DELETE', 9, '2024-12-16 01:30:58', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (57, 'DELETE', 10, '2024-12-16 01:30:58', 'root@localhost', 'liushushu');
INSERT INTO `examlog` VALUES (58, 'UPDATE', 1, '2024-12-16 01:31:03', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (59, 'UPDATE', 4, '2024-12-16 01:31:04', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (60, 'UPDATE', 3, '2024-12-16 01:31:09', 'root@localhost', 'hwr,qzy');
INSERT INTO `examlog` VALUES (61, 'UPDATE', 2, '2024-12-16 01:31:17', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (62, 'UPDATE', 1, '2024-12-16 01:31:33', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (63, 'UPDATE', 1, '2024-12-16 01:33:00', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (64, 'UPDATE', 2, '2024-12-16 01:33:03', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (65, 'UPDATE', 4, '2024-12-16 01:33:15', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (66, 'INSERT', 142, '2024-12-16 20:46:56', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (67, 'UPDATE', 4, '2024-12-16 20:47:55', 'root@localhost', 'liushushu, yang1');
INSERT INTO `examlog` VALUES (68, 'INSERT', 144, '2024-12-16 21:14:37', 'root@localhost', 'hwr');
INSERT INTO `examlog` VALUES (69, 'DELETE', 144, '2024-12-16 21:14:41', 'root@localhost', 'hwr');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `academy` int NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  INDEX `fk_academy`(`academy` ASC) USING BTREE,
  CONSTRAINT `fk_academy` FOREIGN KEY (`academy`) REFERENCES `academy` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'hwr', '123456', NULL, '123456', 'guanliyuan@email163.com', 1, 'http://localhost:8081/file/download/th (1).jpg', 1);
INSERT INTO `user` VALUES (2, 'qzy', '123456', NULL, '123456', 'guanliyuan@email163.com', 1, 'http://localhost:8081/file/download/1719281560982_OIP-C (2).jpg', 0);
INSERT INTO `user` VALUES (3, 'liushushu', '123', NULL, '123456', 'guanliyuan@email163.com', 1, 'http://localhost:8081/file/download/th (2).jpg', 0);
INSERT INTO `user` VALUES (4, 'qzy1', '123', NULL, '123456', 'guanliyuan@email163.com', 2, 'http://localhost:8081/file/download/th.jpg', 0);
INSERT INTO `user` VALUES (5, 'qzy2', '123', NULL, '123456', 'guanliyuan@email163.com', 3, 'http://localhost:8081/file/download/OIP-C (1).jpg', 0);
INSERT INTO `user` VALUES (6, 'yang1', '123', '', '17661945836', '1830699248@qq.com', 2, 'http://localhost:8081/file/download/moren.jpg', 0);

-- ----------------------------
-- View structure for uview
-- ----------------------------
DROP VIEW IF EXISTS `uview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `uview` AS select `user`.`id` AS `id`,`user`.`username` AS `username`,`user`.`password` AS `password`,`user`.`phone` AS `phone`,`user`.`email` AS `email`,`user`.`avatar` AS `avatar`,`academy`.`name` AS `academy`,`user`.`name` AS `name` from (`academy` join `user` on((`academy`.`id` = `user`.`academy`)));

-- ----------------------------
-- Triggers structure for table einfo
-- ----------------------------
DROP TRIGGER IF EXISTS `after_update_einfo`;
delimiter ;;
CREATE TRIGGER `after_update_einfo` AFTER UPDATE ON `einfo` FOR EACH ROW BEGIN
    DECLARE teacher_uid INT;
    DECLARE teacher_username VARCHAR(255);
    DECLARE teacher_list TEXT;
    DECLARE delim CHAR(1) DEFAULT ',';
    DECLARE pos INT DEFAULT 1;

    -- 获取更新后的教师用户名列表
    SET teacher_list = NEW.teacher;

    -- 先删除更新前的教师相关记录
    DELETE FROM exam
    WHERE infoid = OLD.id;

    -- 循环处理每个新的教师用户名
    WHILE LENGTH(teacher_list) > 0 DO
        -- 提取下一个教师用户名
        SET teacher_username = TRIM(SUBSTRING_INDEX(teacher_list, delim, 1));

        -- 查找教师的uid
        SELECT id INTO teacher_uid
        FROM user
        WHERE username = teacher_username
        LIMIT 1;


        -- 向exam表插入新的记录，包含infoid和uid
        INSERT INTO exam (infoid, uid)
        VALUES (NEW.id, teacher_uid);

        -- 去除已处理的教师用户名，继续循环
        SET teacher_list = SUBSTRING(teacher_list, LENGTH(teacher_username) + 2); -- +2 是为了去除逗号和空格
    END WHILE;

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table einfo
-- ----------------------------
DROP TRIGGER IF EXISTS `after_delete_einfo`;
delimiter ;;
CREATE TRIGGER `after_delete_einfo` AFTER DELETE ON `einfo` FOR EACH ROW BEGIN
    DECLARE teacher_uid INT;
    DECLARE teacher_username VARCHAR(255);
    DECLARE teacher_list TEXT;
    DECLARE delim CHAR(1) DEFAULT ',';
    DECLARE pos INT DEFAULT 1;

    -- 获取删除前的教师用户名列表
    SET teacher_list = OLD.teacher;

    -- 循环处理每个教师用户名
    WHILE LENGTH(teacher_list) > 0 DO
        -- 提取下一个教师用户名
        SET teacher_username = TRIM(SUBSTRING_INDEX(teacher_list, delim, 1));

        -- 查找教师的uid
        SELECT id INTO teacher_uid
        FROM user
        WHERE username = teacher_username
        LIMIT 1;


        -- 删除exam表中与该教师相关的记录
        DELETE FROM exam
        WHERE infoid = OLD.id AND uid = teacher_uid;

        -- 去除已处理的教师用户名，继续循环
        SET teacher_list = SUBSTRING(teacher_list, LENGTH(teacher_username) + 2); -- +2 是为了去除逗号和空格
    END WHILE;

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table einfo
-- ----------------------------
DROP TRIGGER IF EXISTS `after_insert_einfo`;
delimiter ;;
CREATE TRIGGER `after_insert_einfo` AFTER INSERT ON `einfo` FOR EACH ROW BEGIN
    DECLARE teacher_uid INT;
    DECLARE teacher_username VARCHAR(255);
    DECLARE teacher_list TEXT;
    DECLARE delim CHAR(1) DEFAULT ',';
    DECLARE pos INT DEFAULT 1;

    -- 获取教师的用户名列表
    SET teacher_list = NEW.teacher;

    -- 循环处理每个教师用户名
    WHILE LENGTH(teacher_list) > 0 DO
        -- 提取下一个教师用户名
        SET teacher_username = TRIM(SUBSTRING_INDEX(teacher_list, delim, 1));

        -- 查找教师的uid
        SELECT id INTO teacher_uid
        FROM user
        WHERE username = teacher_username
        LIMIT 1;


        -- 向exam表插入新的记录，包含infoid和uid
        INSERT INTO exam (infoid, uid)
        VALUES (NEW.id, teacher_uid);

        -- 去除已处理的教师用户名，继续循环
        SET teacher_list = SUBSTRING(teacher_list, LENGTH(teacher_username) + 2); -- +2 是为了去除逗号和空格
    END WHILE;

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table einfo
-- ----------------------------
DROP TRIGGER IF EXISTS `after_insert_einfo1`;
delimiter ;;
CREATE TRIGGER `after_insert_einfo1` AFTER INSERT ON `einfo` FOR EACH ROW BEGIN
    DECLARE operator_name VARCHAR(100);

    -- 获取操作者的名字，可以是当前会话用户的用户名
    -- 假设操作者信息在系统中有办法获取
    -- 例如：使用 SESSION_USER() 或者自定义的方法
    SET operator_name = SESSION_USER();  -- 你可以根据实际情况修改这里的获取方式

    -- 向examlog表插入操作记录
    INSERT INTO examlog (OperationType, InfoID, TeacherID, OperationTime, Operator)
    VALUES ('INSERT', NEW.id, NEW.teacher, NOW(), operator_name);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table einfo
-- ----------------------------
DROP TRIGGER IF EXISTS `after_update_einfo1`;
delimiter ;;
CREATE TRIGGER `after_update_einfo1` AFTER UPDATE ON `einfo` FOR EACH ROW BEGIN
    DECLARE operator_name VARCHAR(100);

    -- 获取操作者的名字，可以是当前会话用户的用户名
    -- 假设操作者信息在系统中有办法获取
    SET operator_name = SESSION_USER();  -- 你可以根据实际情况修改这里的获取方式

    -- 向examlog表插入操作记录
    INSERT INTO examlog (OperationType, InfoID, TeacherID, OperationTime, Operator)
    VALUES ('UPDATE', NEW.id, NEW.teacher, NOW(), operator_name);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table einfo
-- ----------------------------
DROP TRIGGER IF EXISTS `after_delete_einfo1`;
delimiter ;;
CREATE TRIGGER `after_delete_einfo1` AFTER DELETE ON `einfo` FOR EACH ROW BEGIN
    DECLARE operator_name VARCHAR(100);

    -- 获取操作者的名字，可以是当前会话用户的用户名
    -- 假设操作者信息在系统中有办法获取
    SET operator_name = SESSION_USER();  -- 你可以根据实际情况修改这里的获取方式

    -- 向examlog表插入操作记录
    INSERT INTO examlog (OperationType, InfoID, TeacherID, OperationTime, Operator)
    VALUES ('DELETE', OLD.id, OLD.teacher, NOW(), operator_name);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
