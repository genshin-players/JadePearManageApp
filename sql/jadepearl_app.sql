/*
 Navicat Premium Data Transfer

 Source Server         : mainframe
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31-0ubuntu0.20.04.2)
 Source Host           : 39.107.229.253:3306
 Source Schema         : jadepearl_app

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31-0ubuntu0.20.04.2)
 File Encoding         : 65001

 Date: 15/06/2023 14:22:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `display_id` int NOT NULL,
  `signup_number` int NOT NULL COMMENT '报名人数',
  `start_time` datetime NOT NULL COMMENT '报名开始时间，格式”yyyy-MM-dd HH:mm:sss“',
  `end_time` datetime NOT NULL COMMENT '报名截止时间，格式”yyyy-MM-dd HH:mm:sss“',
  `likes` int NOT NULL DEFAULT 0 COMMENT '点赞（热度）',
  `create_time` date NOT NULL DEFAULT 'curtime()' COMMENT '创建日期',
  `update_time` date NOT NULL DEFAULT 'curtime()' COMMENT '更新日期',
  `is_active` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1代表TRUE(生效),0代表FALSE（不生效）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `activities_display_id_fk`(`display_id` ASC) USING BTREE,
  CONSTRAINT `activities_display_id_fk` FOREIGN KEY (`display_id`) REFERENCES `display` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activities
-- ----------------------------
INSERT INTO `activities` VALUES (1, 5, 5, '2023-06-09 15:56:55', '2023-06-09 15:56:58', 114514, '2023-06-09', '2023-06-09', 1);
INSERT INTO `activities` VALUES (3, 14, 11, '2023-06-13 15:45:32', '2023-06-13 15:45:36', 1919810, '2023-06-13', '2023-06-13', 1);
INSERT INTO `activities` VALUES (12, 102, 23, '2023-06-15 09:00:00', '2023-06-30 09:00:00', 0, '2023-06-15', '2023-06-15', 1);

-- ----------------------------
-- Table structure for attendence
-- ----------------------------
DROP TABLE IF EXISTS `attendence`;
CREATE TABLE `attendence`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_id` int NOT NULL COMMENT '班级id',
  `student_id` int NOT NULL COMMENT '学生id',
  `date` datetime NOT NULL COMMENT '日期，格式”yyyy-MM-dd HH:mm:sss“',
  `is_present` int NOT NULL COMMENT '0.缺勤 1.迟到 2.在 3.请假',
  `report_user_id` int NOT NULL COMMENT '汇报者id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `attendence_classes_id_fk`(`class_id` ASC) USING BTREE,
  INDEX `attendence_users_id_fk`(`report_user_id` ASC) USING BTREE,
  INDEX `attendence_users_id_fk2`(`student_id` ASC) USING BTREE,
  CONSTRAINT `attendence_classes_id_fk` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `attendence_users_id_fk2` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '出勤' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendence
-- ----------------------------
INSERT INTO `attendence` VALUES (1, 1, 8, '2023-06-10 22:21:07', 2, 17);
INSERT INTO `attendence` VALUES (2, 1, 9, '2023-06-10 22:21:07', 2, 17);
INSERT INTO `attendence` VALUES (3, 1, 10, '2023-06-10 22:21:07', 2, 17);
INSERT INTO `attendence` VALUES (4, 2, 11, '2023-06-10 22:21:07', 2, 18);
INSERT INTO `attendence` VALUES (5, 2, 12, '2023-06-10 22:21:07', 1, 18);
INSERT INTO `attendence` VALUES (6, 2, 13, '2023-06-10 22:21:07', 3, 18);
INSERT INTO `attendence` VALUES (7, 3, 14, '2023-06-10 22:21:07', 0, 19);
INSERT INTO `attendence` VALUES (8, 3, 15, '2023-06-10 22:21:07', 0, 19);
INSERT INTO `attendence` VALUES (9, 3, 16, '2023-06-10 22:21:07', 0, 19);
INSERT INTO `attendence` VALUES (21, 1, 8, '2023-06-12 14:00:00', 0, 17);
INSERT INTO `attendence` VALUES (22, 1, 9, '2023-06-12 14:00:00', 0, 17);
INSERT INTO `attendence` VALUES (23, 1, 10, '2023-06-12 14:00:00', 1, 17);
INSERT INTO `attendence` VALUES (24, 2, 11, '2023-06-12 14:00:00', 2, 17);
INSERT INTO `attendence` VALUES (25, 2, 12, '2023-06-12 14:00:00', 2, 17);
INSERT INTO `attendence` VALUES (26, 2, 13, '2023-06-12 14:00:00', 2, 17);
INSERT INTO `attendence` VALUES (27, 3, 14, '2023-06-12 00:00:00', 1, 17);
INSERT INTO `attendence` VALUES (28, 3, 15, '2023-06-12 00:00:00', 2, 17);
INSERT INTO `attendence` VALUES (29, 3, 16, '2023-06-12 00:00:00', 0, 17);
INSERT INTO `attendence` VALUES (33, 1, 8, '2023-06-11 00:00:00', 2, 19);
INSERT INTO `attendence` VALUES (34, 1, 9, '2023-06-11 00:00:00', 2, 19);
INSERT INTO `attendence` VALUES (35, 1, 10, '2023-06-11 00:00:00', 2, 17);

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `instructor_id` int NOT NULL COMMENT '教员id',
  `adviser_id` int NOT NULL COMMENT '辅导员id(班主任)',
  `create_time` date NOT NULL DEFAULT 'curdate()',
  `subject_id` int NOT NULL COMMENT '班级类型id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `classes_users_id_fk`(`instructor_id` ASC) USING BTREE,
  INDEX `classes_users_id_fk2`(`adviser_id` ASC) USING BTREE,
  INDEX `classes_subjects_id_fk`(`subject_id` ASC) USING BTREE,
  CONSTRAINT `classes_subjects_id_fk` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `classes_users_id_fk` FOREIGN KEY (`instructor_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `classes_users_id_fk2` FOREIGN KEY (`adviser_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '班级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES (1, '艾欧尼亚班', 4, 7, '2023-06-09', 1);
INSERT INTO `classes` VALUES (2, '暗影岛班', 5, 2, '2023-06-09', 1);
INSERT INTO `classes` VALUES (3, '诺克萨斯班', 6, 3, '2023-06-09', 2);

-- ----------------------------
-- Table structure for display
-- ----------------------------
DROP TABLE IF EXISTS `display`;
CREATE TABLE `display`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标题',
  `content` varchar(5000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '内容',
  `display_type_id` int NOT NULL COMMENT '内容类型',
  `cover_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '封面图片（待定）',
  `create_time` datetime NOT NULL DEFAULT 'curtime()',
  `update_time` datetime NOT NULL DEFAULT 'curtime()',
  `publish_user_id` int NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `display_display_type_id_fk`(`display_type_id` ASC) USING BTREE,
  INDEX `display_users_id_fk`(`publish_user_id` ASC) USING BTREE,
  CONSTRAINT `display_display_type_id_fk` FOREIGN KEY (`display_type_id`) REFERENCES `display_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `display_users_id_fk` FOREIGN KEY (`publish_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '显示内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of display
-- ----------------------------
INSERT INTO `display` VALUES (5, '辩论赛', NULL, 1, '1', '2023-06-11 19:54:44', '2023-06-11 19:54:46', 1, 1);
INSERT INTO `display` VALUES (6, '学院优秀作品展示', NULL, 3, '1', '2023-06-11 20:22:13', '2023-06-11 20:22:15', 1, 1);
INSERT INTO `display` VALUES (10, '安倍晋三堂々復活', NULL, 2, '1', '2023-06-13 00:25:27', '2023-06-13 00:25:28', 1, 1);
INSERT INTO `display` VALUES (11, '肯尼迪遭遇枪击后生还', NULL, 2, '1', '2023-06-13 00:26:07', '2023-06-13 00:26:08', 1, 1);
INSERT INTO `display` VALUES (12, '丫丫眼圈被美国人打黑了', NULL, 2, '1', '2023-06-13 00:26:30', '2023-06-13 00:26:31', 1, 1);
INSERT INTO `display` VALUES (13, '双子塔重建', NULL, 2, '1', '2023-06-13 00:26:58', '2023-06-13 00:27:00', 1, 1);
INSERT INTO `display` VALUES (14, '篮球比赛', NULL, 1, '1', '2023-06-13 15:43:42', '2023-06-13 15:43:44', 1, 1);
INSERT INTO `display` VALUES (102, '炫酷坦克杂技射击表演', '<p>坦克！射击！Student！</p><p>本次活动每班报名人数为：<span style=\"font-size:18px;\">23</span>！</p><p>活动报名时间为：<span style=\"font-size:18px;\"><i><strong>2023-06-15 09:00:00</strong></i></span>至<span style=\"font-size:18px;\"><i><strong>2023-06-30 09:00:00</strong></i></span></p>', 1, '1', '2023-06-15 12:04:11', '2023-06-15 12:04:12', 1, 1);

-- ----------------------------
-- Table structure for display_element
-- ----------------------------
DROP TABLE IF EXISTS `display_element`;
CREATE TABLE `display_element`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `display_id` int NOT NULL COMMENT '显示id',
  `element_type_id` int NOT NULL COMMENT '元素类型id',
  `routine` int NOT NULL COMMENT '顺序（第一段，第二段等等）',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `display_element_display_id_fk`(`display_id` ASC) USING BTREE,
  INDEX `display_element_display_element_type_id_fk`(`element_type_id` ASC) USING BTREE,
  CONSTRAINT `display_element_display_element_type_id_fk` FOREIGN KEY (`element_type_id`) REFERENCES `display_element_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `display_element_display_id_fk` FOREIGN KEY (`display_id`) REFERENCES `display` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '显示元素' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of display_element
-- ----------------------------

-- ----------------------------
-- Table structure for display_element_type
-- ----------------------------
DROP TABLE IF EXISTS `display_element_type`;
CREATE TABLE `display_element_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '例:文字，图片，音频，视频',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '显示元素类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of display_element_type
-- ----------------------------

-- ----------------------------
-- Table structure for display_type
-- ----------------------------
DROP TABLE IF EXISTS `display_type`;
CREATE TABLE `display_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '例:班级上课展示、师资力量、校园背景、学员作品展示、学校通报、排班汇报',
  `is_inner` tinyint(1) NOT NULL COMMENT '是否为对内展示，1.对内（true） 2.对外（false）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '显示类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of display_type
-- ----------------------------
INSERT INTO `display_type` VALUES (1, '内部活动', 1);
INSERT INTO `display_type` VALUES (2, '每日一推', 1);
INSERT INTO `display_type` VALUES (3, '对外展览', 0);

-- ----------------------------
-- Table structure for member_schedules
-- ----------------------------
DROP TABLE IF EXISTS `member_schedules`;
CREATE TABLE `member_schedules`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL COMMENT '工作学社成员id',
  `date` date NOT NULL COMMENT '工作日期',
  `work_type_id` int NOT NULL COMMENT '工作类型id',
  `create_time` date NOT NULL DEFAULT 'curdate()',
  `update_time` date NOT NULL,
  `create_user_id` int NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '完成状态 0未完成，1已完成',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `member_schedules_users_id_fk`(`member_id` ASC) USING BTREE,
  INDEX `member_schedules_member_schedules_type_id_fk2`(`work_type_id` ASC) USING BTREE,
  CONSTRAINT `member_schedules_member_schedules_type_id_fk` FOREIGN KEY (`work_type_id`) REFERENCES `member_schedules_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `member_schedules_member_schedules_type_id_fk2` FOREIGN KEY (`work_type_id`) REFERENCES `member_schedules_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `member_schedules_users_id_fk` FOREIGN KEY (`member_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '学社成员工作安排' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_schedules
-- ----------------------------
INSERT INTO `member_schedules` VALUES (1, 17, '2023-06-13', 1, '2023-06-13', '2023-06-13', 1, 0);
INSERT INTO `member_schedules` VALUES (2, 17, '2023-06-14', 2, '2023-06-14', '2023-06-14', 1, 1);

-- ----------------------------
-- Table structure for member_schedules_type
-- ----------------------------
DROP TABLE IF EXISTS `member_schedules_type`;
CREATE TABLE `member_schedules_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '例：查寝，点名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '工作类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_schedules_type
-- ----------------------------
INSERT INTO `member_schedules_type` VALUES (1, '查班');
INSERT INTO `member_schedules_type` VALUES (2, '早迎接');

-- ----------------------------
-- Table structure for member_work_class
-- ----------------------------
DROP TABLE IF EXISTS `member_work_class`;
CREATE TABLE `member_work_class`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `schedules_id` int NOT NULL,
  `class_id` int NOT NULL,
  `create_time` datetime NOT NULL DEFAULT 'curtime()',
  `update_time` datetime NOT NULL DEFAULT 'curtime()',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `member_work_class_classes_id_fk`(`class_id` ASC) USING BTREE,
  INDEX `member_work_class_member_schedules_id_fk`(`schedules_id` ASC) USING BTREE,
  CONSTRAINT `member_work_class_classes_id_fk` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `member_work_class_member_schedules_id_fk` FOREIGN KEY (`schedules_id`) REFERENCES `member_schedules` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_work_class
-- ----------------------------
INSERT INTO `member_work_class` VALUES (2, 1, 3, '2023-06-15 14:21:51', '2023-06-15 14:21:51');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `level` int NOT NULL,
  `parent_id` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '用户管理', 1, 0);
INSERT INTO `menu` VALUES (2, '活动管理', 1, 0);
INSERT INTO `menu` VALUES (3, '学社工作', 1, 0);
INSERT INTO `menu` VALUES (4, '我的信息', 1, 0);
INSERT INTO `menu` VALUES (5, '班级管理', 1, 0);
INSERT INTO `menu` VALUES (6, '教师管理', 2, 1);
INSERT INTO `menu` VALUES (7, '学生管理', 2, 1);
INSERT INTO `menu` VALUES (8, '每日一推', 2, 2);
INSERT INTO `menu` VALUES (9, '学社活动\n', 2, 2);
INSERT INTO `menu` VALUES (10, '对外展览', 2, 2);
INSERT INTO `menu` VALUES (11, '学社出勤', 2, 3);
INSERT INTO `menu` VALUES (12, '班级出勤', 2, 3);
INSERT INTO `menu` VALUES (13, '学社工作安排', 2, 3);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色id',
  `menu_id` int NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_menu_menu_id_fk`(`menu_id` ASC) USING BTREE,
  INDEX `role_menu_roles_id_fk`(`role_id` ASC) USING BTREE,
  CONSTRAINT `role_menu_menu_id_fk` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_menu_roles_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, 1);
INSERT INTO `role_menu` VALUES (2, 1, 2);
INSERT INTO `role_menu` VALUES (3, 1, 3);
INSERT INTO `role_menu` VALUES (4, 1, 4);
INSERT INTO `role_menu` VALUES (5, 1, 5);
INSERT INTO `role_menu` VALUES (6, 1, 6);
INSERT INTO `role_menu` VALUES (7, 1, 7);
INSERT INTO `role_menu` VALUES (8, 1, 8);
INSERT INTO `role_menu` VALUES (9, 1, 9);
INSERT INTO `role_menu` VALUES (10, 1, 10);
INSERT INTO `role_menu` VALUES (11, 1, 11);
INSERT INTO `role_menu` VALUES (12, 1, 12);
INSERT INTO `role_menu` VALUES (13, 1, 13);
INSERT INTO `role_menu` VALUES (14, 2, 1);
INSERT INTO `role_menu` VALUES (15, 2, 2);
INSERT INTO `role_menu` VALUES (16, 2, 3);
INSERT INTO `role_menu` VALUES (17, 2, 4);
INSERT INTO `role_menu` VALUES (18, 2, 5);
INSERT INTO `role_menu` VALUES (19, 2, 7);
INSERT INTO `role_menu` VALUES (20, 2, 8);
INSERT INTO `role_menu` VALUES (21, 2, 9);
INSERT INTO `role_menu` VALUES (22, 2, 10);
INSERT INTO `role_menu` VALUES (23, 2, 11);
INSERT INTO `role_menu` VALUES (24, 2, 12);
INSERT INTO `role_menu` VALUES (25, 2, 13);
INSERT INTO `role_menu` VALUES (26, 3, 5);
INSERT INTO `role_menu` VALUES (27, 5, 2);
INSERT INTO `role_menu` VALUES (28, 5, 8);
INSERT INTO `role_menu` VALUES (29, 5, 9);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, '管理员');
INSERT INTO `roles` VALUES (2, '学社社长');
INSERT INTO `roles` VALUES (3, '班主任');
INSERT INTO `roles` VALUES (4, '教员');
INSERT INTO `roles` VALUES (5, '学社部长');
INSERT INTO `roles` VALUES (6, '学生');

-- ----------------------------
-- Table structure for signup
-- ----------------------------
DROP TABLE IF EXISTS `signup`;
CREATE TABLE `signup`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `adivser_id` int NOT NULL COMMENT '辅导员（班主任）id',
  `class_id` int NOT NULL COMMENT '班级id',
  `activity_id` int NOT NULL COMMENT '活动id',
  `signup_student_id` int NOT NULL COMMENT '报名学生id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `signup_activities_id_fk`(`activity_id` ASC) USING BTREE,
  INDEX `signup_classes_id_fk`(`class_id` ASC) USING BTREE,
  INDEX `signup_users_id_fk`(`signup_student_id` ASC) USING BTREE,
  INDEX `signup_users_id_fk2`(`adivser_id` ASC) USING BTREE,
  CONSTRAINT `signup_activities_id_fk` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `signup_classes_id_fk` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `signup_users_id_fk` FOREIGN KEY (`signup_student_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `signup_users_id_fk2` FOREIGN KEY (`adivser_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '报名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of signup
-- ----------------------------

-- ----------------------------
-- Table structure for student_class
-- ----------------------------
DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL COMMENT '学生id',
  `class_id` int NOT NULL COMMENT '班级id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_class_classes_id_fk`(`class_id` ASC) USING BTREE,
  INDEX `student_class_users_id_fk`(`student_id` ASC) USING BTREE,
  CONSTRAINT `student_class_classes_id_fk` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_class_users_id_fk` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '学生与班级绑定表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_class
-- ----------------------------
INSERT INTO `student_class` VALUES (1, 7, 1);
INSERT INTO `student_class` VALUES (2, 4, 1);
INSERT INTO `student_class` VALUES (3, 8, 1);
INSERT INTO `student_class` VALUES (4, 9, 1);
INSERT INTO `student_class` VALUES (5, 10, 1);
INSERT INTO `student_class` VALUES (6, 2, 2);
INSERT INTO `student_class` VALUES (7, 5, 2);
INSERT INTO `student_class` VALUES (8, 11, 2);
INSERT INTO `student_class` VALUES (9, 12, 2);
INSERT INTO `student_class` VALUES (10, 13, 2);
INSERT INTO `student_class` VALUES (11, 3, 3);
INSERT INTO `student_class` VALUES (12, 6, 3);
INSERT INTO `student_class` VALUES (13, 14, 3);
INSERT INTO `student_class` VALUES (14, 15, 3);
INSERT INTO `student_class` VALUES (15, 16, 3);
INSERT INTO `student_class` VALUES (16, 17, 1);
INSERT INTO `student_class` VALUES (17, 18, 2);
INSERT INTO `student_class` VALUES (18, 19, 3);

-- ----------------------------
-- Table structure for subjects
-- ----------------------------
DROP TABLE IF EXISTS `subjects`;
CREATE TABLE `subjects`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'accp, UI, 大数据等等',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '班级类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subjects
-- ----------------------------
INSERT INTO `subjects` VALUES (1, 'ACCP');
INSERT INTO `subjects` VALUES (2, 'UI');
INSERT INTO `subjects` VALUES (3, '大数据');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(70) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `role_id` int NOT NULL,
  `account_info` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'json格式，账号信息，包含用户名密码（待定）',
  `identity_info` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'json格式，身份信息，包含姓名、年龄、性别、联系方式等',
  `create_time` date NOT NULL DEFAULT 'curdate()',
  `update_time` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `users_roles_id_fk`(`role_id` ASC) USING BTREE,
  CONSTRAINT `users_roles_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户（所有学生，老师，管理员）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'linyining', '19970717', 1, NULL, '{\"realname\":\"林忆宁\",\"age\":\"25\",\"gender\":\"女\",\"phone\":\"13361887757\",\"identity\":\"310106199707170028\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (2, 'VIEGO', 'viego', 3, NULL, '{\"realname\":\"佛耶戈\",\"age\":\"20\",\"gender\":\"男\",\"phone\":\"13611111111\",\"identity\":\"341722111111111111\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (3, 'SWAIN', 'wain', 3, NULL, '{\"realname\":\"斯维因\",\"age\":\"50\",\"gender\":\"男\",\"phone\":\"13611111112\",\"identity\":\"341722111111111112\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (4, 'IRELIA', 'irelia', 4, NULL, '{\"realname\":\"艾瑞莉娅\",\"age\":\"18\",\"gender\":\"女\",\"phone\":\"13611111113\",\"identity\":\"341722111111111113\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (5, 'THRESH', 'thresh', 4, NULL, '{\"realname\":\"锤石\",\"age\":\"50\",\"gender\":\"男\",\"phone\":\"13611111114\",\"identity\":\"341722111111111114\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (6, 'DARIUS', 'darius', 4, NULL, '{\"realname\":\"诺克萨斯之手\",\"age\":\"50\",\"gender\":\"男\",\"phone\":\"13611111115\",\"identity\":\"341722111111111115\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (7, 'MASTERYI', 'materyi', 3, NULL, '{\"realname\":\"易\",\"age\":\"30\",\"gender\":\"男\",\"phone\":\"13611111110\",\"identity\":\"341722111111111110\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (8, 'KAYN', 'kayn', 6, NULL, '{\"realname\":\"凯隐\",\"age\":\"30\",\"gender\":\"男\",\"phone\":\"13611111116\",\"identity\":\"341722111111111116\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (9, 'YASUO', 'yasuo', 6, NULL, '{\"realname\":\"亚索\",\"age\":\"18\",\"gender\":\"男\",\"phone\":\"13611111117\",\"identity\":\"341722111111111117\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (10, 'YONE', 'yone', 6, NULL, '{\"realname\":\"永恩\",\"age\":\"18\",\"gender\":\"男\",\"phone\":\"13611111118\",\"identity\":\"341722111111111118\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (11, 'GWEN', 'gwen', 6, NULL, '{\"realname\":\"格温\",\"age\":\"18\",\"gender\":\"女\",\"phone\":\"13611111119\",\"identity\":\"341722111111111119\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (12, 'KALISTA', 'kalista', 6, NULL, '{\"realname\":\"卡莉斯塔\",\"age\":\"18\",\"gender\":\"女\",\"phone\":\"13611111120\",\"identity\":\"341722111111111120\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (13, 'VEX', 'vex', 6, NULL, '{\"realname\":\"薇古丝\",\"age\":\"18\",\"gender\":\"女\",\"phone\":\"13611111121\",\"identity\":\"341722111111111121\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (14, 'SAMIRA', 'samira', 6, NULL, '{\"realname\":\"沙弥拉\",\"age\":\"18\",\"gender\":\"女\",\"phone\":\"13611111122\",\"identity\":\"341722111111111122\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (15, 'DRAVEN', 'draven', 6, NULL, '{\"realname\":\"德莱文\",\"age\":\"20\",\"gender\":\"男\",\"phone\":\"13611111123\",\"identity\":\"341722111111111123\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (16, 'SION', 'sion', 6, NULL, '{\"realname\":\"赛恩\",\"age\":\"200\",\"gender\":\"男\",\"phone\":\"13611111124\",\"identity\":\"341722111111111124\"}', '2023-06-09', '2023-06-09');
INSERT INTO `users` VALUES (17, 'member1', 'member1', 5, NULL, '{\"realname\":\"学社成员1\",\"age\":\"20\",\"gender\":\"男\",\"phone\":\"13611111125\",\"identity\":\"341722111111111125\"}', '2023-06-10', '2023-06-10');
INSERT INTO `users` VALUES (18, 'member2', 'member2', 5, NULL, '{\"realname\":\"学社成员2\",\"age\":\"20\",\"gender\":\"男\",\"phone\":\"13611111126\",\"identity\":\"341722111111111126\"}', '2023-06-10', '2023-06-10');
INSERT INTO `users` VALUES (19, 'member3', 'member3', 5, NULL, '{\"realname\":\"学社成员3\",\"age\":\"20\",\"gender\":\"男\",\"phone\":\"13611111127\",\"identity\":\"341722111111111127\"}', '2023-06-10', '2023-06-10');

SET FOREIGN_KEY_CHECKS = 1;
