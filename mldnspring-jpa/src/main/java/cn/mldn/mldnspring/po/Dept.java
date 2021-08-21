package cn.mldn.mldnspring.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Cacheable(true) 								
@Entity
public class Dept implements Serializable {
	@Id													// 主键列
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deptno;								// 字段的映射（属性名称=字段名称） 
	private double avgsal;
	@Temporal(TemporalType.DATE)						// 类型描述
	private Date createdate;
	private String dname;
	private int num;
	// setter、getter略 
	public Long getDeptno() {
		return deptno;
	}
	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}
	public double getAvgsal() {
		return avgsal;
	}
	public void setAvgsal(double avgsal) {
		this.avgsal = avgsal;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", avgsal=" + avgsal + ", createdate=" + createdate + ", dname=" + dname
				+ ", num=" + num + "]";
	}
	
}
