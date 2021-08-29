package cn.mldn.mldnspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mldn.mldnspring.po.Tag;

public interface ITagDAO extends JpaRepository<Tag, Long> {

}
 