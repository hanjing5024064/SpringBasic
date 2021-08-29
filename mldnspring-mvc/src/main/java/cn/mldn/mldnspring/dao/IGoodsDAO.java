package cn.mldn.mldnspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mldn.mldnspring.po.Goods;

public interface IGoodsDAO extends JpaRepository<Goods, Long> {

}
  