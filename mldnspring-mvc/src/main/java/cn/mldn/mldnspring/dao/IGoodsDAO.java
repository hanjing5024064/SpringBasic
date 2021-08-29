package cn.mldn.mldnspring.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.mldn.mldnspring.po.Goods;

public interface IGoodsDAO extends JpaRepository<Goods, Long> {
	@Query("SELECT g FROM Goods AS g WHERE g.name LIKE :#{'%' + #keyWord + '%'} AND g.dflag=:dflag")
	public List<Goods> findSplit(@Param(value = "keyWord") String keyWord, @Param(value = "dflag") Integer dflag,
			Pageable page);

	@Query("SELECT COUNT(g) FROM Goods AS g WHERE g.name LIKE :#{'%' + #keyWord + '%'} AND g.dflag=:dflag")
	public Long getSplitCount(@Param(value = "keyWord") String keyWord, @Param(value = "dflag") Integer dflag);

	@Query(nativeQuery = true, value = "SELECT tid FROM goods_tag WHERE gid=:gid")
	public List<Long> findTidByGoods(@Param(value = "gid") Long gid);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Goods AS g SET dflag=:dflag WHERE gid IN :gids")
	public Integer editDflag(@Param(value = "gids") Set<Long> gids, @Param(value = "dflag") Integer dflag);

	@Query("SELECT g FROM Goods AS g WHERE g.dflag=:dflag")
	public List<Goods> findAllByDflag(@Param(value = "dflag") Integer dflag, Pageable page);
	
	@Query("SELECT COUNT(g) FROM Goods AS g WHERE g.dflag=:dflag")
	public Long getAllCountByDflag(@Param(value = "dflag") Integer dflag);
}
