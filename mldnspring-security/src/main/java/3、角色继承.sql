-- 使用数据库
USE mldn ;
CREATE TABLE role_hierarchies(
	includingrole		VARCHAR(50),
	includedrole		VARCHAR(50)
);
INSERT INTO role_hierarchies(includingrole,includedrole) VALUES('ROLE_ADMIN','ROLE_USER') ;