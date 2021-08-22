DROP DATABASE IF EXISTS mldn ;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE company(
	cid			BIGINT	AUTO_INCREMENT ,
	cname		VARCHAR(50) ,
	CONSTRAINT pk_cid PRIMARY KEY(cid) 
) engine = innodb ;
CREATE TABLE details(
	did			BIGINT	AUTO_INCREMENT ,
	address		VARCHAR(50) ,
	capital		DOUBLE,
	cid			BIGINT ,
	CONSTRAINT pk_did PRIMARY KEY(did) ,
	CONSTRAINT fk_cid FOREIGN KEY(cid) REFERENCES company(cid) ON DELETE CASCADE
) engine = innodb ;

