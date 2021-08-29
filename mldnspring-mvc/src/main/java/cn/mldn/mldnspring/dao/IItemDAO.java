package cn.mldn.mldnspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mldn.mldnspring.po.Item;

public interface IItemDAO extends JpaRepository<Item, Long> {

}
