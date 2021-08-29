package cn.mldn.mldnspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mldn.mldnspring.po.Member;

public interface IMemberDAO extends JpaRepository<Member, String> {

}
