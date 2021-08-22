package cn.mldn.mldnspring.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
@SuppressWarnings("serial")
public class Details implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long did ;
	private String address ; 
	private Double capital ;
	@OneToOne(fetch=FetchType.EAGER)								// 一对一关联
	@JoinColumn(name="cid",
		referencedColumnName="cid",unique=true)		// 设置关联数据列
	private Company company ;						// 公司详情属于一个公司
	// setter、getter、toString()略
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getCapital() {
		return capital;
	}
	public void setCapital(Double capital) {
		this.capital = capital;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
}

