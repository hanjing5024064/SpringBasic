package cn.mldn.mldnspring.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@SuppressWarnings("serial")
@Entity
public class Item implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long iid ;
	private String title ;
	@OneToMany(mappedBy="item") 		// 一对多关联
	private List<Goods> goodses ;
	// setter、getter、toString()略
	public Long getIid() {
		return iid;
	}
	public void setIid(Long iid) {
		this.iid = iid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Goods> getGoodses() {
		return goodses;
	}
	public void setGoodses(List<Goods> goodses) {
		this.goodses = goodses;
	}
	
}
