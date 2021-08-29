package cn.mldn.mldnspring.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.mldn.mldnspring.po.Goods;

public interface IGoodsDAO extends JpaRepository<Goods, Long> {
	@Query("SELECT g FROM Goods AS g WHERE g.name LIKE :#{'%' + #keyWord + '%'}")
	public List<Goods> findSplit(@Param(value = "keyWord") String keyWord,Pageable page);
	@Query("SELECT COUNT(g) FROM Goods AS g WHERE g.name LIKE :#{'%' + #keyWord + '%'}")
	public Long getSplitCount(@Param(value = "keyWord") String keyWord) ;
}
  