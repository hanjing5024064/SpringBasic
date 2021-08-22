package cn.mldn.mldnspring.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Company implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long cid ;
	private String cname ;
	@OneToMany(mappedBy="company",cascade=CascadeType.PERSIST)	// 一对多关联
	private List<Dept> depts;			// 公司包含部门信息
	// setter、getter、toString()略
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public List<Dept> getDepts() {
		return depts;
	}
	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}
	
}

