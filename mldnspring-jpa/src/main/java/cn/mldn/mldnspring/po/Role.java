package cn.mldn.mldnspring.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
@SuppressWarnings("serial")
public class Role implements Serializable {
	@Id
	private String rid;
	private String title;
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY,cascade=CascadeType.ALL)	// 多对多关联
	private List<Member> members;

	// setter、getter、toString()略
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
}

