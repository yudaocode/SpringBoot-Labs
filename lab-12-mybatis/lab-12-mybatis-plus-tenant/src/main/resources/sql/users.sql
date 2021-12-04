CREATE TABLE `users`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
    `username`    varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
    `password`    varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
    `create_time` datetime                        DEFAULT NULL COMMENT '创建时间',
    `deleted`     bit(1)                          DEFAULT NULL COMMENT '是否删除。0-未删除；1-删除',
    `tenant_id`   int(11) NOT NULL COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `user_profile`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `user_id`   int(11) NOT NULL COMMENT '用户编号',
    `gender`    int(11) NOT NULL COMMENT '性别',
    `deleted`   bit(1) DEFAULT NULL COMMENT '是否删除。0-未删除；1-删除',
    `tenant_id` int(11) NOT NULL COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
