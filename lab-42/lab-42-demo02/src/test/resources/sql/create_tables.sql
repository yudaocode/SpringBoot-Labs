CREATE TABLE `t_user` (
   `id` INT AUTO_INCREMENT  PRIMARY KEY COMMENT '用户编号',
   `username` VARCHAR(64) NOT NULL COMMENT '账号',
   `password` VARCHAR(64) NOT NULL COMMENT '密码'
);
