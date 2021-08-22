DROP DATABASE IF EXISTS mldn ;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE company(
	cid			BIGINT	AUTO_INCREMENT ,
	cname		VARCHAR(50) ,
	CONSTRAINT pk_cid PRIMARY KEY(cid) 
) engine = innodb ;
CREATE TABLE dept(
	deptno		BIGINT	AUTO_INCREMENT ,
	dname		VARCHAR(50) ,
	cid			BIGINT ,
	CONSTRAINT pk_deptno PRIMARY KEY(deptno) ,
	CONSTRAINT fk_cid FOREIGN KEY(cid) REFERENCES company(cid) ON DELETE CASCADE
) engine = innodb ;

