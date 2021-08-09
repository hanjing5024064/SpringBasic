package cn.mldn.mldnspring.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 			// 设置要使用的测试工具
public class TestJdbcTemplate {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(TestJdbcTemplate.class) ;
	@Autowired
	private JdbcTemplate jdbcTemplate ;				// 注入JdbcTemplate对象
	@Test
	public void testAdd() throws Exception {
		String sql = "INSERT INTO news(title,pubdate,note,price,readcount) VALUES (?,?,?,?,?)" ;
		String title = "MLDN魔乐科技" ;
		Date pubdate = new Date() ;
		String note = "技术教学：www.mldn.cn" ;
		double price = 19800.0 ;
		int readcount = 567000 ;
		int len = this.jdbcTemplate.update(sql, title, 
				pubdate, note, price, readcount); 	// 数据更新操作
		this.logger.info("更新行数：" + len);			// 日志输出
	} 
}
