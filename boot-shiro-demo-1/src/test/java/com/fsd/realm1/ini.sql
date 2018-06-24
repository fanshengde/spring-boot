只用了user表
create table users(
	sid integer,
	username varchar(200),
	password varchar(200),
	primary key(sid)
);

create table user_roles(
	sid integer,
	primary key (sid)
);
	
	
create table roles_permissions(
	sid integer,
	primary key(sid)
);