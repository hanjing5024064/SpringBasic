DROP DATABASE IF EXISTS mldn ;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE member (
	mid					VARCHAR(50),
	name				VARCHAR(50),
	CONSTRAINT pk_mid PRIMARY KEY (mid)
) engine = innodb;
-- 2、角色数据表
CREATE TABLE role (
	rid					VARCHAR(50) ,
	title				VARCHAR(50),
	CONSTRAINT pk_rid PRIMARY KEY (rid)
) engine = innodb;
-- 3、用户-角色关系表
CREATE TABLE member_role (
	mid					VARCHAR(50),
	rid 				VARCHAR(50) ,
	CONSTRAINT fk_mid1 FOREIGN KEY(mid) REFERENCES member(mid) ,
	CONSTRAINT fk_rid1 FOREIGN KEY(rid) REFERENCES role(rid)
) engine = innodb;
INSERT INTO role(rid,title) VALUES ('company','公司管理') ;
INSERT INTO role(rid,title) VALUES ('dept','部门管理') ;
INSERT INTO role(rid,title) VALUES ('emp','雇员管理') ;
INSERT INTO role(rid,title) VALUES ('salgrade','薪资管理') ;
INSERT INTO role(rid,title) VALUES ('sale','销售管理') ;
INSERT INTO role(rid,title) VALUES ('market','市场管理') ;
INSERT INTO role(rid,title) VALUES ('project','项目管理') ;

