package cn.mldn.mldnspring.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class) // 设置要使用的测试工具
public class TestDataSource {
	@Autowired
	private DataSource dataSource ;			// 注入DataSource对象
	@Test
	public void testConnection() throws Exception {
		System.out.println(this.dataSource.getConnection());
	} 
}
