create database demo;

use demo;



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

insert into sys_role_user(id, SYS_USER_ID,sys_ROLES_ID) values(1,1,1);
insert into sys_role_user(id, SYS_USER_ID,sys_ROLES_ID) values(2,2,2);

INSERT INTO `sys_permission` VALUES ('1', 'ROLE_HOME', 'home', '/', null,null), ('2', 'ROLE_ADMIN', 'ABel', '/admin', null,null);

INSERT INTO `sys_permission_role` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '2', '1');

alter table sys_user drop id;
alter table sys_user add sid int not null primary key;
