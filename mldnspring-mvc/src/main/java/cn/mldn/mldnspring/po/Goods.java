package cn.mldn.mldnspring.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@SuppressWarnings("serial")
@Entity
public class Goods implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long gid ;
	private String name ;
	private Double price ;
	private String photo ;
	private Integer dflag ;
	@ManyToOne(fetch=FetchType.LAZY)					// 多对一关联 
	@JoinColumn(name="iid")		// 设置关联字段
	private Item item ;
	@ManyToMany(fetch = FetchType.LAZY)					// 启用延迟加载
	@JoinTable(											// 描述的是一个关联表
		name="goods_tag" ,								// 定义中间表名称
		joinColumns = { @JoinColumn(name = "gid") }	,	// goods与goods_tag表的连接
		inverseJoinColumns = { 
				@JoinColumn(name = "tid") }) 			// 通过Goods找到Tag中的tid数据
	private List<Tag> tags ; 							// 商品对应标签信息
	// setter、getter、toString()略
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getDflag() {
		return dflag;
	}
	public void setDflag(Integer dflag) {
		this.dflag = dflag;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
}
