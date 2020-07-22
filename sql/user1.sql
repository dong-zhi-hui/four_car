# 修改level int
ALTER TABLE `user`
MODIFY COLUMN `level`  int(11) NULL DEFAULT NULL COMMENT '0:普通用户 1:普通会员 2:高级会员 3:管理员' AFTER `create_time`;

