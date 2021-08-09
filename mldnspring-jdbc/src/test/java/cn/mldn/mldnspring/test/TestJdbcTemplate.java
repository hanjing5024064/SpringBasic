package cn.mldn.mldnspring.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 			// 设置要使用的测试工具
public class TestJdbcTemplate {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(TestJdbcTemplate.class) ;
	@Autowired
	private JdbcTemplate jdbcTemplate ;				// 注入JdbcTemplate对象

	@Test
	public void testDelete() {
		String sql = "DELETE FROM news WHERE nid=?";
		long nid = 2;
		int len = this.jdbcTemplate.update(sql, nid); 		// 更新操作
		this.logger.info("更新行数：" + len);					// 日志输出
	}

	
	@Test
	public void testEdit() throws Exception {
		String sql = "UPDATE news SET title=?,pubdate=?,note=?,price=?,readcount=? WHERE nid=?" ;
		String title = "极限IT程序员" ;
		Date pubdate = new Date() ;
		String note = "线上培训：www.jixianit.com" ;
		double price = 3980.0 ;
		int readcount = 7867000 ;
		long nid = 3 ; 
		int len = this.jdbcTemplate.update(sql, title, 
				pubdate, note, price, readcount,nid); 	 	// 数据更新操作
		this.logger.info("更新行数：" + len);					// 日志输出
	} 	
	
	
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

	@Test
	public void testAddPreparedStatementSetter() throws Exception {
		String sql = "INSERT INTO news(title,pubdate,note,price,readcount) VALUES (?,?,?,?,?)" ;
		String title = "MLDN魔乐科技" ;
		Date pubdate = new Date() ;
		String note = "技术教学：www.mldn.cn" ;
		double price = 19800.0 ;
		int readcount = 567000 ;
		int len = this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, title);
				ps.setDate(2, new java.sql.Date(pubdate.getTime()));
				ps.setString(3, note);
				ps.setDouble(4, price);
				ps.setInt(5, readcount); 
			}
		}) ; 
		this.logger.info("更新行数：" + len);			// 日志输出
	} 
	@Test
	public void testAddKeyHolder() throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder() ;	// 获得增长后的ID数据
		String sql = "INSERT INTO news(title,pubdate,note,price,readcount) VALUES (?,?,?,?,?)" ;
		String title = "MLDN魔乐科技" ;
		Date pubdate = new Date() ;
		String note = "技术教学：www.mldn.cn" ;
		double price = 19800.0 ;
		int readcount = 567000 ;
		int len = this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, title);
				ps.setDate(2, new java.sql.Date(pubdate.getTime()));
				ps.setString(3, note);
				ps.setDouble(4, price);
				ps.setInt(5, readcount);
				return ps;
			}

		}, keyHolder);
		this.logger.info("更新行数：" + len + "、增长后的ID：" + keyHolder.getKey());			// 日志输出
	} 
	
}
