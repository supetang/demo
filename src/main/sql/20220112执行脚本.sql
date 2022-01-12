CREATE TABLE
IF NOT EXISTS t_user (
    `id` VARCHAR (32) NOT NULL COMMENT '主键',
    `userName` VARCHAR (32) DEFAULT NULL COMMENT '用户名',
    `sex` TINYINT (1) NOT NULL DEFAULT '0' NULL COMMENT '0:男  1：女 ',
    `phoneNumBer` VARCHAR (32) DEFAULT NULL COMMENT '手机号码',
    `isDelete` INT (1) DEFAULT '0' COMMENT '逻辑删除标识(0.未删除，1.已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '用户名';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
     `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
     `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
     `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
     `version` int(1) NULL DEFAULT 1 COMMENT '乐观锁',
     `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
     `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
     `deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

