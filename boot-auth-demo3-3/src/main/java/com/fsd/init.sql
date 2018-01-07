create database auth5;

use auth5;

CREATE TABLE sys_user (
	id INT NOT NULL PRIMARY KEY,
	username VARCHAR (200),
	PASSWORD VARCHAR (200)
);

CREATE TABLE sys_role (
	id INT NOT NULL PRIMARY KEY,
	NAME VARCHAR (200)
);

create table sys_permission(
	id int not null primary key,
	name VARCHAR(200),
	descritption varchar(200),
	url varchar(200),
	pid int,
	method varchar(200)
);

create table sys_role_user(
	id int not null primary key,
	sys_user_id int,
	sys_roles_id int
);
alter table sys_role_user add CONSTRAINT fk_SysRoleUser_sysUserId_To_SysUser_id FOREIGN key(sys_user_id) REFERENCES sys_user(id);
alter table sys_role_user add constraint fk_SysRoleUser_sysRoleId_To_SysrRole_id FOREIGN key(sys_roles_id) references sys_role(id);

create table sys_permission_role(
	id int not null primary key,
	roles_id int,
	permission_id int
);


insert into sys_user (id,username, password) values (1,'admin', '123');
insert into sys_user (id,username, password) values (2,'abel', '123');

insert into sys_role(id,name) values(1,'ROLE_ADMIN');
insert into sys_role(id,name) values(2,'ROLE_USER');

insert into sys_role_user(id, sys_user_id,sys_roles_id) values(1,1,1);
insert into sys_role_user(id, sys_user_id,sys_roles_id) values(2,2,2);

INSERT INTO `sys_permission` VALUES ('1', 'ROLE_HOME', 'home', '/', null,null), ('2', 'ROLE_ADMIN', 'ABel', '/admin', null,null);

INSERT INTO `sys_permission_role` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '2', '1');


INSERT INTO `auth5`.`sys_permission` (`id`, `name`, `descritption`, `url`, `method`) VALUES ('3', 'ROLE_USER_GET', 'user', '/user/**', 'GET');
INSERT INTO `auth5`.`sys_permission` (`id`, `name`, `descritption`, `url`, `method`) VALUES ('4', 'ROLE_USER_POST', 'user', '/user/**', 'POST');
INSERT INTO `auth5`.`sys_permission` (`id`, `name`, `descritption`, `url`, `method`) VALUES ('5', 'ROLE_USER_PUT', 'user', '/user/**', 'PUT');
INSERT INTO `auth5`.`sys_permission` (`id`, `name`, `descritption`, `url`, `method`) VALUES ('6', 'ROLE_USER_ALL', 'user', '/user/**', 'ALL');
UPDATE `auth5`.`sys_permission` SET `method`='GET' WHERE `id`='1';
UPDATE `auth5`.`sys_permission` SET `method`='POST' WHERE `id`='2';


INSERT INTO `auth5`.`sys_permission_role` (`id`, `roles_id`, `permission_id`) VALUES ('4', '1', '6');
INSERT INTO `auth5`.`sys_permission_role` (`id`, `roles_id`, `permission_id`) VALUES ('5', '2', '1');
INSERT INTO `auth5`.`sys_permission_role` (`id`, `roles_id`, `permission_id`) VALUES ('6', '2', '3');


