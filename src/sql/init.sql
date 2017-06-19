drop database if exists stu;
create database stu;
use stu;

drop table IF EXISTS t_user;
drop table IF EXISTS t_emp;
drop table IF EXISTS t_dep;

create table t_user(
	username varchar(100) primary key,
	password varchar(100),
	nickname varchar(100)
);

create table t_dep(
	id int(10) primary key AUTO_INCREMENT,
	name varchar(100)
);

create table t_emp(
	id int(10) primary key AUTO_INCREMENT,
	name varchar(100),
	sex varchar(10),
	salary double,
	dep_id int(10),
	CONSTRAINT FOREIGN KEY (dep_id) REFERENCES t_dep(id) 
);

insert into t_user values ('admin','123','Super Admin');