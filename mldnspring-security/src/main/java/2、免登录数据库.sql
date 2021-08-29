-- 使用数据库
USE mldn ;
-- 创建数据表保存免登录信息
CREATE TABLE persistent_logins (   
	series			VARCHAR(64) ,   
	username		VARCHAR(100) ,   
	token 			VARCHAR(64) ,   
	last_used 		TIMESTAMP ,
	CONSTRAINT pk_series PRIMARY KEY(series)
);  
