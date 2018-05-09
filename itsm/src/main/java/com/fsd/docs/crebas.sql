/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/2/16 23:09:41                           */
/*==============================================================*/


/*==============================================================*/
/* Table: PermissionInfo                                        */
/*==============================================================*/
create table PermissionInfo
(
   sid                  int not null,
   sname                varchar(20),
   permissionUrl        varchar(200),
   method               varchar(20),
   description          varchar(100),
   primary key (sid)
);

/*==============================================================*/
/* Table: RoleInfo                                              */
/*==============================================================*/
create table RoleInfo
(
   sid                  int not null,
   sname                varchar(20),
   roleLevel            int,
   description          varchar(1000),
   menuItems            varchar(100),
   primary key (sid)
);

/*==============================================================*/
/* Table: UserInfo                                              */
/*==============================================================*/
create table UserInfo
(
   sid                  int not null,
   cnname               varchar(20),
   username             varchar(20),
   password             varchar(40),
   rePassword           varchar(40),
   historyPassword      varchar(40),
   email                varchar(100),
   telephone            varchar(20),
   mobilePhone          varchar(20),
   primary key (sid)
);

/*==============================================================*/
/* Table: rolePermission                                        */
/*==============================================================*/
create table rolePermission
(
   sid                  int not null,
   role_id              int,
   permission_id        int,
   primary key (sid)
);

/*==============================================================*/
/* Table: userRole                                              */
/*==============================================================*/
create table userRole
(
   sid                  int not null,
   user_id              int,
   role_id              int,
   primary key (sid)
);

alter table rolePermission add constraint FK_Reference_3 foreign key (role_id)
      references RoleInfo (sid) on delete restrict on update restrict;

alter table rolePermission add constraint FK_Reference_4 foreign key (permission_id)
      references PermissionInfo (sid) on delete restrict on update restrict;

alter table userRole add constraint FK_Reference_1 foreign key (user_id)
      references UserInfo (sid) on delete restrict on update restrict;

alter table userRole add constraint FK_Reference_2 foreign key (role_id)
      references RoleInfo (sid) on delete restrict on update restrict;

