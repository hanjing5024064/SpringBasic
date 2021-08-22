package cn.mldn.mldnspring.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Member implements Serializable {
	@Id
	private String mid;
	private String name;
	@ManyToMany(fetch = FetchType.LAZY)						// 启用延迟加载
	@JoinTable(												// 描述的是一个关联表
		name="member_role" ,								// 定义中间表名称
		joinColumns = { @JoinColumn(name = "mid") }	,		// 描述的是member与member_role表的连接
		inverseJoinColumns = { @JoinColumn(name = "rid") }) // 通过Member找到Role中的rid的数据
	private List<Role> roles;
	// setter、getter、toString()略
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

