create database auth3;
use auth3;

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
	pid int,
	method varchar(200)
);
create table sys_role_user(
	id int not null primary key,
	sys_user_id int,
	Sys_Role_id int
);


insert into Sys_User (id,username, password) values (1,'admin', 'admin');
insert into Sys_User (id,username, password) values (2,'abel', 'abel');

insert into Sys_Role(id,name) values(1,'ROLE_ADMIN');
insert into Sys_Role(id,name) values(2,'ROLE_USER');


insert into sys_role_user(id,sys_user_id,Sys_Role_id) values(1,1,1);
insert into sys_role_user(id,SYS_USER_ID,Sys_Role_id) values(2,2,2);

select * from Sys_User;