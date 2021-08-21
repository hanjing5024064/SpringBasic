DROP DATABASE mldn ;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE dept(
	deptno		BIGINT ,
	dname		VARCHAR(50) ,
	createdate	DATE,
	num			INT ,
	avgsal		DOUBLE ,
	CONSTRAINT pk_deptno PRIMARY KEY(deptno)
) engine = innodb ;
CREATE TABLE table_id_generate(
	digid		BIGINT	AUTO_INCREMENT ,
	id_key		VARCHAR(50) ,
	id_value	BIGINT(50) ,
	CONSTRAINT pk_digid PRIMARY KEY(digid)
) engine = innodb ;
INSERT INTO table_id_generate(id_key,id_value) VALUES ('COMPANY_ID',1000) ;
INSERT INTO table_id_generate(id_key,id_value) VALUES ('DEPT_ID',6789) ;
INSERT INTO table_id_generate(id_key,id_value) VALUES ('EMP_ID',7369) ;
