package cn.mldn.mldnspring.dbc;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MySQLDatabaseConnectDemo {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;			// 驱动程序
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/mldn?serverTimezone=GMT&useSSL=false" ;	// 连接地址
	public static final String USERNAME = "root" ;							// 用户名
	public static final String PASSWORD = "MyMac#123" ;					// 密码
	public static void main(String[] args) throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource() ;
		dataSource.setDriverClassName(DRIVER); 								// 设置驱动
		dataSource.setUrl(URL);												// 设置地址
		dataSource.setUsername(USERNAME);									// 设置用户名
		dataSource.setPassword(PASSWORD);									// 设置密码
		System.out.println(dataSource.getConnection());						// 检测连接
		dataSource.getConnection().close(); 								// 关闭连接
	}
}
