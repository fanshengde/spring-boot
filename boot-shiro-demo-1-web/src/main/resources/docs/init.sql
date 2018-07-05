INSERT INTO `sys_user` (`sid`,`username`,`name`,`password`,`salt`,`state`) VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0);
INSERT INTO `sys_user` (`sid`,`username`,`name`,`password`,`salt`,`state`) VALUES ('27', 'vip', 'vip用户', '5b787401aec66168e8802d162b3268ef', '9d29fa39d9af9617d3f1fe78d697c1f5', 0);
INSERT INTO `sys_user` (`sid`,`username`,`name`,`password`,`salt`,`state`) VALUES ('28', 'test', '管理员', 'ddc9966c72ddaad565aa020da287af3e', 'd3a971b7e1a47f2470d07c9666331cb3', 0);


INSERT INTO `sys_permission` (`sid`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (1,0,'用户管理',0,'0/','userInfo:view','menu','userInfo/userList');
INSERT INTO `sys_permission` (`sid`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (2,0,'用户添加',1,'0/1','userInfo:add','button','userInfo/userAdd');
INSERT INTO `sys_permission` (`sid`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (3,0,'用户删除',1,'0/1','userInfo:del','button','userInfo/userDel');


INSERT INTO `sys_role` (`sid`,`available`,`description`,`role`) VALUES (1,0,'管理员','admin');
INSERT INTO `sys_role` (`sid`,`available`,`description`,`role`) VALUES (2,0,'VIP会员','vip');
INSERT INTO `sys_role` (`sid`,`available`,`description`,`role`) VALUES (3,1,'test','test');

INSERT INTO `sys_role_permission` VALUES ('1', '1');

INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (1,1);
INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (2,1);
INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (3,2);

INSERT INTO `sys_user_role` (`role_id`,`user_id`) VALUES (1,1);
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (2,27);
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (3,28);