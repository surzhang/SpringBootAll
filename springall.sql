/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : springall

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 14/01/2022 14:07:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_dept
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept`;
CREATE TABLE `tbl_dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int(11) DEFAULT 0 COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_dept
-- ----------------------------
INSERT INTO `tbl_dept` VALUES (101, '研发部门', 1, '0', '0', 'Enzo', '2018-03-16 11:33:00', 'Enzo', '2018-03-16 11:33:00');
INSERT INTO `tbl_dept` VALUES (102, '市场部门', 2, '0', '0', 'Enzo', '2018-03-16 11:33:00', 'Enzo', '2018-03-16 11:33:00');
INSERT INTO `tbl_dept` VALUES (103, '测试部门', 3, '0', '0', 'Enzo', '2018-03-16 11:33:00', 'Enzo', '2020-12-24 10:27:48');
INSERT INTO `tbl_dept` VALUES (104, '财务部门', 4, '0', '0', 'Enzo', '2018-03-16 11:33:00', 'Enzo', '2018-03-16 11:33:00');
INSERT INTO `tbl_dept` VALUES (105, '运维部门', 5, '0', '0', 'Enzo', '2018-03-16 11:33:00', 'Enzo', '2018-03-16 11:33:00');
INSERT INTO `tbl_dept` VALUES (108, '人力资源部', 8, '0', '0', 'chenjian', '2021-12-07 15:24:24', 'zyk', '2021-12-08 20:53:30');
INSERT INTO `tbl_dept` VALUES (112, 'kkk', NULL, NULL, NULL, 'zyk', NULL, NULL, NULL);
INSERT INTO `tbl_dept` VALUES (118, 'pppp', 5, '0', NULL, NULL, '2021-12-15 09:59:46', 'zyk', '2021-12-15 12:20:32');
INSERT INTO `tbl_dept` VALUES (119, 'gggg', 9, '0', NULL, 'zyk', '2021-12-15 11:54:49', 'zyk', '2021-12-15 12:20:49');
INSERT INTO `tbl_dept` VALUES (120, 'bbbb', 2, '0', NULL, 'zyk', '2021-12-15 13:00:27', 'zyk', '2021-12-15 13:01:13');
INSERT INTO `tbl_dept` VALUES (123, 'yyyy', 888, '0', NULL, 'zyk', '2021-12-16 15:44:57', NULL, NULL);
INSERT INTO `tbl_dept` VALUES (124, '填啥是', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tbl_question
-- ----------------------------
DROP TABLE IF EXISTS `tbl_question`;
CREATE TABLE `tbl_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题编号',
  `question` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `option_a` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `option_b` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `option_c` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `option_d` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` int(11) DEFAULT 0 COMMENT '预留字段',
  `status` int(11) DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `updater` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `flag` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '问题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_question
-- ----------------------------
INSERT INTO `tbl_question` VALUES (1, '我的人生观是', 'A人生的体验越多越好，所以想法很多，有可能就应该多尝试。', 'B深度比宽度更重要，目标要谨慎，一旦确定就坚持到底。', 'C人生必须有所成。', 'D没必要太辛苦，好好活着就行。', 1, 0, '2021-11-23 11:13:13', 'zhangsan', '2021-12-01 16:08:34', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (2, '如果野外旅行，在下山返回的路线上，我更在乎：', 'A要好玩有趣，不愿重复，所以宁愿走新路线。', 'B要安全稳妥，担心危险，所以宁愿走原路线。', 'C要挑战自我，喜欢冒险，所以宁愿走新路线。', 'D要方便省心，害怕麻烦，所以宁愿走原路线。', 1, 0, '2021-11-23 11:13:13', 'chenjian', '2021-11-23 11:13:13', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (3, '在表达一件事情上，别人认为我：', 'A总是给人感受到强烈印象。', 'B总是表述极其准确。', 'C总能围绕最终目的。', 'D总能让大家很舒服。', 1, 0, '2021-11-23 11:13:13', 'chenjian', '2021-11-23 11:13:13', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (4, '在生命多数时候，我其实更希望：', 'A刺激。', 'B安全。', 'C挑战。', 'D稳定。', 1, 0, '2021-11-23 11:13:13', 'chenjian', '2021-11-23 11:13:13', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (5, '我认为自己在情感上的基本特点是：', 'A情绪多变，情绪波动大。', 'B外表抑制强，但内心起伏大，一旦挫伤难以平复。', 'C感情不拖泥带水，较直接。', 'D天性四平八稳。', 1, 0, '2021-11-23 11:13:13', 'chenjian', '2021-11-23 11:13:13', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (6, '我认为自己除了工作以外，在人生的控制欲上，我：', 'A谈不上控制欲，却有强烈地感染带动他人的欲望，但自控能力不强。', 'B用规则来保持我的自控和对他人的要求。', 'C内心有控制欲，希望别人服从我。', 'D从不愿去管别人，也不愿别人来管我。', 1, 0, '2021-11-23 11:13:13', 'chenjian', '2021-11-23 11:13:13', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (7, '当与情人交往时，我倾向于：', 'A在一起时就要尽情地欢乐，爱意常会溢于言表。', 'B体贴入微关怀细腻，于对方的需求和变化极其敏感。', 'C帮助对方成长是我最大的责任。', 'D迁就顺从的陪伴者和绝佳的聆听着。', 1, 0, '2021-11-23 11:13:13', 'chenjian', '2021-11-23 11:13:13', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (8, '在人际交往时，我：', 'A心态开放，可快速建立起人际关系。', 'B非常审慎缓慢地深入，一旦认为是朋友便会长久。', 'C希望在人际关系中占据主导地位。', 'D顺其自然，不温不火，相对被动。', 1, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (9, '我认为自己的为人：', 'A可爱而生机。', 'B深沉而内敛。', 'C果断而自信。', 'D平静而和气。', 1, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (10, '我完成任务的方式是：', 'A常赶在最后期限前的一刻完成。', 'B自己精确地做，不麻烦别人。', 'C最快速做完，再找下一个任务。', 'D该怎么做就怎么做，需要时从他人处得到帮忙。', 1, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (11, '如果有人深深惹恼我，我：', 'A内心手上，当时认为不可能原谅，但最终常会原谅对方。', 'B如此之深的愤怒无法忘记，同时未来避开那个家伙。', 'C每个人都要为他的错误付出相应的代价，内心期望有机会要狠狠的回应。', 'D尽量不摊牌，因为还不到那个地步。', 1, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (12, '在人际关系中，我最在意的是：', 'A欢迎。', 'B理解。', 'C尊敬。', 'D接纳。', 1, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (13, '在工作上，我表现出更多的是：', 'A热忱，有很多想法且很多灵性。', 'B完美精准且承诺可靠。', 'C坚强而有推动力。', 'D有耐心且适应性强。', 1, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (14, '我过往的老师最有可能对我的评价是：', 'A善于表达和抒发情感。', 'B严格保护自己的私密，有时会显得孤独或不合群。', 'C动作敏捷独立，且喜欢自己做事情。', 'D反应度偏低，比较温和。', 1, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (15, '朋友对我的评价最有可能的是：', 'A喜欢对朋友倾诉事情，是开心果。', 'B能提出很多问题，且需要许多精细的解说。', 'C解决问题的高手。', 'D总能多听少说。', 1, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (16, '在帮助他人的问题上，我倾向于：', 'A我不主动，但若他来找我，那我一定帮。', 'B值得帮助的人就帮。', 'C无关者何必帮，但我若承诺，必完成。', 'D虽无英雄打虎胆，常有自告奋勇心。', 2, 0, '2021-11-23 11:13:14', 'chenjian', '2021-11-23 11:13:14', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (17, '面对他人对自己的赞美，我的本能反应是：', 'A没有赞美也无所谓，得到了也不至于欣喜。', 'B我无须那些没用的赞美，宁可他们欣赏我的能力。', 'C有点怀疑对方是否认真或立即回避很多人的关注。', 'D能得到赞美，总归是一件令人愉悦的事。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (18, '面对生活的现状，我更倾向于：', 'A外面怎样与我无关，我觉得自己这样还行。', 'B这个世界如果我不进步，别人就会进步，所以我需要不停地前进。', 'C在所有的问题未发生前，就该尽量想好所有的可能性。', 'D每天的生活，只要开心快乐最重要。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (19, '对于规则，我内心的态度是：', 'A不愿违反规则，但可能因为松散而无法达到规则要求。', 'B打破规则，希望由自己来制定规则，而不是遵守规则。', 'C严格遵守规则，且竭尽全力做到规则内的最好。', 'D不喜欢被规则束缚，不按规则出牌，会觉得新鲜有趣。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (20, '我认为自己做事上：', 'A慢条斯理，按部就班，能与周围协调一致。', 'B目标明确，集中精力为实现目标而努力，善于抓住核心。', 'C慎重小心，为做好预防及善后，会尽心操劳。', 'D丰富跃动，灵活反应。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (21, '在面对压力时，我比较倾向于选用：', 'A眼不见为净。', 'B压力越大，抵抗力越大。', 'C在自己的内心慢慢地咀嚼消化压力。', 'D本能地回避压力，避不掉就用各种方法发泄出去。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (22, '当结束一段刻骨铭心的感情时，我会：', 'A日子总要过，时间会冲淡一切。', 'B虽然受伤，但一旦下定决心，就会努力把过去的影子甩掉。', 'C深陷悲伤，在相当长的时间里难以自拔，也不愿再接受新的人。', 'D痛不欲生，需要找朋友倾诉，寻求化解之道。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (23, '面对他人的痛苦倾诉，我回顾自己大多数时候本能上倾?', 'A静静地听，认同对方的感受。', 'B作出判断，痛苦没用，要帮助对方解决问题。', 'C给予分析，帮助他分析，安抚他的情绪。', 'D发表自己的评论意见，与对方的情绪共起落。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (24, '我在以下哪个群体中较感满足？', 'A能心平气和，只要大家达成一致。', 'B能简单扼要有结果地彼此展开充分的辩论。', 'C能就一件事有规则、有条理、有步骤、有章法地详细讨论。', 'D能随意无拘束地、开心地自由谈话。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (25, '我觉得工作：', 'A最好没有压力，让我做我熟悉的工作就不错。', 'B是达成人生目标和成就最重要的途径。', 'C要么不做，要做就做到最好。', 'D如果能将乐趣融合在工作中就太棒了，如果是不喜欢的工作就实在没劲。', 2, 0, '2021-11-23 11:13:15', 'chenjian', '2021-11-23 11:13:15', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (26, '如果我是领导，我内心更希望在部属的心目中，我是：', 'A可以亲近的和善于为他们着想的。', 'B有很强的能力和富有领导力的。', 'C公平公正且足以信赖的。', 'D被他们喜欢并且觉得富有感召力的。', 2, 0, '2021-11-23 11:13:16', 'chenjian', '2021-11-23 11:13:16', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (27, '我希望得到的认同方式是：', 'A有无认同都不要影响我。', 'B精英的认同最重要。', 'C我认同的人或我在乎的人认同即可。', 'D希望大家都能认同我。', 2, 0, '2021-11-23 11:13:16', 'chenjian', '2021-11-23 11:13:16', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (29, '如果我是父母，我也许是：', 'A不愿干涉子女或易被说动的。', 'B严厉的或直接给予方向指点的。', 'C用行动代替语言来表示关爱或高要求的。', 'D愿意陪孩子一起玩，孩子的朋友们所喜欢和欢迎的。', 2, 0, '2021-11-23 11:13:16', 'chenjian', '2021-11-23 11:13:16', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (30, '以下有四组格言，哪组里符合我感觉的数目最多？', 'A最深刻的真理是最简单和最平凡的。', 'B走自己的路，让人家去说吧。', 'C一个不注意小事的人，永远不会成就大事。', 'D与其在死的时候握着一大把钱，还不如活时活得丰富多彩。', 2, 0, '2021-11-23 11:13:16', 'chenjian', '2021-11-23 11:13:16', 'chenjian', 0);
INSERT INTO `tbl_question` VALUES (31, '你喜欢的是谁？', 'A欢迎。', 'B理解。', 'C尊敬。', 'D接纳。', 1, 0, NULL, NULL, NULL, NULL, 1);
INSERT INTO `tbl_question` VALUES (32, 'df', 'de', 'ss', 'string', 'string', 0, 0, '2022-01-13 22:13:21', 'df', '2022-01-13 12:23:23', 'fff', 0);
INSERT INTO `tbl_question` VALUES (33, 'ffff', '搜索树', 'dddd', 'ff', 'sss', NULL, 0, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for tbl_tester
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tester`;
CREATE TABLE `tbl_tester`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '测试者姓名',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `create_time` datetime(0) DEFAULT NULL COMMENT '测试时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tbl_tester_phone_uindex`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '性格测试者' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_tester
-- ----------------------------
INSERT INTO `tbl_tester` VALUES (1, '张三', '15517546710', '2021-12-06 16:21:09');
INSERT INTO `tbl_tester` VALUES (2, '李四', '15514546824', '2021-08-07 23:27:32');
INSERT INTO `tbl_tester` VALUES (3, '牛牛', '14444564525', '2021-08-08 08:44:49');
INSERT INTO `tbl_tester` VALUES (4, '牛大', '15515517564', '2021-09-08 08:51:47');
INSERT INTO `tbl_tester` VALUES (5, '熊大', '16645685462', '2021-10-08 08:54:43');
INSERT INTO `tbl_tester` VALUES (6, '看了看', '15514652485', '2021-10-08 16:28:27');
INSERT INTO `tbl_tester` VALUES (7, '王五', '14414576520', '2021-10-08 19:17:15');
INSERT INTO `tbl_tester` VALUES (8, '刘厢', '18845724562', '2021-10-08 19:55:23');
INSERT INTO `tbl_tester` VALUES (9, '小风', '16617546825', '2021-10-08 20:42:58');
INSERT INTO `tbl_tester` VALUES (10, '小风', '17714525682', '2021-10-08 20:43:14');
INSERT INTO `tbl_tester` VALUES (11, '沐雨', '14417546852', '2021-10-08 20:46:00');
INSERT INTO `tbl_tester` VALUES (14, '欧阳风', '18814575264', '2021-11-09 10:07:31');
INSERT INTO `tbl_tester` VALUES (15, '给爷', '14414525462', '2021-11-09 10:22:36');
INSERT INTO `tbl_tester` VALUES (18, '天天', '18817546582', '2021-11-09 11:23:26');
INSERT INTO `tbl_tester` VALUES (25, '水草', '16661542546', '2021-11-09 20:04:18');
INSERT INTO `tbl_tester` VALUES (26, '猪八戒', '15545625845', '2021-12-09 20:08:38');
INSERT INTO `tbl_tester` VALUES (27, '石头', '13315412542', '2021-12-09 20:22:30');
INSERT INTO `tbl_tester` VALUES (28, '安其拉', '14417546854', '2021-12-09 20:31:31');
INSERT INTO `tbl_tester` VALUES (29, '追梦人', '18817715546', '2021-12-09 20:36:11');
INSERT INTO `tbl_tester` VALUES (30, '程咬金', '17747745546', '2021-12-09 20:38:28');
INSERT INTO `tbl_tester` VALUES (31, '凯爹', '18817745620', '2021-12-09 20:51:04');
INSERT INTO `tbl_tester` VALUES (32, '干将', '16614520354', '2021-12-09 20:55:50');
INSERT INTO `tbl_tester` VALUES (33, '李元芳', '15845245826', '2021-12-09 20:58:21');
INSERT INTO `tbl_tester` VALUES (34, '后羿', '17717717752', '2021-12-09 21:30:34');
INSERT INTO `tbl_tester` VALUES (35, '火影', '16614525842', '2021-12-09 22:04:11');
INSERT INTO `tbl_tester` VALUES (36, '狄仁杰', '17714525630', '2021-12-09 22:46:47');
INSERT INTO `tbl_tester` VALUES (37, '哪吒', '16614517825', '2021-12-09 22:48:30');
INSERT INTO `tbl_tester` VALUES (38, '黄忠', '16612325205', '2021-12-09 22:53:03');
INSERT INTO `tbl_tester` VALUES (39, '孙尚香', '14416585245', '2021-12-09 23:02:26');
INSERT INTO `tbl_tester` VALUES (40, '影劫', '12545265232', '2021-12-09 23:14:10');
INSERT INTO `tbl_tester` VALUES (41, '刘德华', '14417546858', '2021-12-09 23:30:04');
INSERT INTO `tbl_tester` VALUES (42, '孟肖通', '13065630697', '2021-12-10 08:55:02');
INSERT INTO `tbl_tester` VALUES (43, '大大大', '15517546730', '2021-12-10 10:17:50');
INSERT INTO `tbl_tester` VALUES (44, '张三', '16614514424', '2021-12-11 15:40:46');
INSERT INTO `tbl_tester` VALUES (45, '金蟾', '12345678924', '2021-12-11 15:43:37');

-- ----------------------------
-- Table structure for tbl_upload
-- ----------------------------
DROP TABLE IF EXISTS `tbl_upload`;
CREATE TABLE `tbl_upload`  (
  `upload_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '即文件ID，为方便识别命名为表名_id',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原文件名',
  `destination_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务器文件名',
  PRIMARY KEY (`upload_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_upload
-- ----------------------------
INSERT INTO `tbl_upload` VALUES (1, 'hjjh', '发送');
INSERT INTO `tbl_upload` VALUES (2, '测试', '哈哈');
INSERT INTO `tbl_upload` VALUES (3, 'k.png', 'http://oss-cn-beijing.aliyuncs.com76fcb47a-1d12-4c37-9dd2-320e96008105k.png');
INSERT INTO `tbl_upload` VALUES (4, 'a.jpg', 'https://exampledemo.oss-cn-beijing.aliyuncs.com/6c717c5e-0cd9-4522-b33e-fdc7d125345ca.jpg');
INSERT INTO `tbl_upload` VALUES (5, 'a.jpg', 'https://exampledemo.oss-cn-beijing.aliyuncs.com/11d8f814-9e52-45ab-96e2-7c85cd91bd42a.jpg');

SET FOREIGN_KEY_CHECKS = 1;
