create database auth4;
use auth4;

CREATE TABLE Sys_User (
	id INT NOT NULL PRIMARY KEY,
	username VARCHAR (200),
	PASSWORD VARCHAR (200)
);

CREATE TABLE Sys_Role (
	id INT NOT NULL PRIMARY KEY,
	NAME VARCHAR (200)
);
create table Sys_Permission(
	id int not null primary key,
	name VARCHAR(200),
	descritption varchar(200),
	url varchar(200),
	pid int
);
create table sys_role_user(
	id int not null primary key,
	sys_user_id int,
	Sys_Role_id int
);
create table Sys_permission_role(
	id int not null primary key,
    role_id int,
    permission_id int
);


insert into Sys_User (id,username, password) values (1,'admin', '123');
insert into Sys_User (id,username, password) values (2,'abel', '123');

insert into Sys_Role(id,name) values(1,'ROLE_ADMIN');
insert into Sys_Role(id,name) values(2,'ROLE_USER');

insert into sys_role_user(id,sys_user_id,Sys_Role_id) values(1,1,1);
insert into sys_role_user(id,sys_user_id,Sys_Role_id) values(2,2,2);


INSERT INTO `Sys_Permission` VALUES ('1', 'ROLE_HOME', 'home', '/', null), ('2', 'ROLE_ADMIN', 'ABel', '/admin', null);

INSERT INTO `Sys_permission_role` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '2', '1');
