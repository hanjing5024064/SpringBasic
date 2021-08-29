package cn.mldn.mldnspring.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.mldn.mldnspring.po.Role;

public interface IRoleDAO extends JpaRepository<Role, String> {
	/**
	 * 根据用户ID查询对应的角色ID
	 * @param mid 用户ID
	 * @return 用户拥有的全部角色ID
	 */
	@Query(nativeQuery=true,value="SELECT rid FROM member_role WHERE mid=:mid")
	public Set<String> findAllByMember(@Param("mid") String mid) ;
}
