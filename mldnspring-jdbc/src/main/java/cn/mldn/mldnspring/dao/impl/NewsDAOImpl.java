package cn.mldn.mldnspring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.mldn.mldnspring.dao.INewsDAO;
import cn.mldn.mldnspring.vo.News;
@Repository
public class NewsDAOImpl implements INewsDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	@Override
	public News findById(Long nid) {
		System.out.println("************* 执行数据库查询 ****************");
		String sql = "SELECT nid,title,pubdate,note,price,readcount FROM news WHERE nid=?" ;
		// 实现数据查询，传入所需要的SQL语句与参数（参数要通过对象数组包装），由于查询还需要通过RowMapper处理
		News vo = null ;
		try {
			vo = this.jdbcTemplate.queryForObject(sql, new Object[] {nid} , new RowMapper<News>() {
				@Override 
				public News mapRow(ResultSet rs, int rowNum) throws SQLException {
					News vo = new News() ;
					vo.setNid(rs.getLong(1));
					vo.setTitle(rs.getString(2));
					vo.setPubdate(rs.getDate(3));
					vo.setNote(rs.getString(4));
					vo.setPrice(rs.getDouble(5));
					vo.setReadcount(rs.getInt(6));
					return vo;
				}}) ;
		} catch (Exception e) {}
		System.out.println("vo = " + vo);
		return vo ;
	}

	@Override
	public boolean doUpdate(News vo) {
		System.out.println("************ 数据库更新操作 *********");
		String sql = "UPDATE news SET title=?,pubdate=?,note=?,price=?,readcount=? WHERE nid=?" ;
		int len = this.jdbcTemplate.update(sql, vo.getTitle(), vo.getPubdate(), vo.getNote(), 
				vo.getPrice(), vo.getReadcount(), vo.getNid()); 	// 数据更新操作
		return len > 0 ;
	}
}
