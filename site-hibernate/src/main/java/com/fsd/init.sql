create database test_hbm;

use test_hbm;

CREATE TABLE USER_INFO(
SID int not null primary key, 
user_name varchar(200), 
user_password varchar(200), 
sex varchar(10));
