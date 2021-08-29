package cn.mldn.mldnspring.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.mldn.mldnspring.dao.IMemberDAO;
import cn.mldn.mldnspring.dao.IRoleDAO;
import cn.mldn.mldnspring.po.Member;
@Service(value="userDetailsService")			// 通过注解配置，此名称要在spring-security.xml中使用
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private IMemberDAO memberDAO ;				// 注入用户数据操作接口
	@Autowired
	private IRoleDAO roleDAO ;					// 注入角色操作接口
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Member> optional = this.memberDAO.findById(username); // 根据用户ID进行查询
		if (!optional.isPresent()) {			// 用户信息不存在
			throw new UsernameNotFoundException("用户“" + username + "”信息不存在，无法进行登录。");
		}
		Member member = optional.get() ; 		// 获取用户对象
		// 用户对应的所有角色信息需要通过GrantedAuthority集合保存
		List<GrantedAuthority> allGrantedAuthority = new ArrayList<GrantedAuthority>(); 	// 保存授权信息
		Set<String> allRoles = this.roleDAO.findAllByMember(username) ; 	// 获取用户对应的全部角色信息
		Iterator<String> roleIter = allRoles.iterator();					// 迭代输出角色信息
		while (roleIter.hasNext()) {
			allGrantedAuthority.add(new SimpleGrantedAuthority(roleIter.next()));	// 保存角色信息
		}
		boolean enabled = member.getEnabled().equals(1) ; 					// 判断用户存在状态
		UserDetails user = new User(username, member.getPassword(), enabled, 
				true, true, true, allGrantedAuthority);						// 
		return user ;														// 返回UserDetails对象 
	}

}
