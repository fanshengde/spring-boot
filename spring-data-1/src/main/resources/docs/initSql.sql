create table customer(
	sid int not null,
	firstname varchar(20),
	lastname varchar(20),
	emailAddress varchar(100),
	street varchar(50),
	city varchar(30),
	country varchar(20),
	primary key(sid)
);



create table address(
	sid int not null,
	street varchar(50),
	city varchar(30),
	country varchar(20),
	primary key(sid)
);

alter table customer add column addresses_sid int;

alter table customer add constraint FK_customer_2_address_address foreign key (addresses_sid) references address (sid);
