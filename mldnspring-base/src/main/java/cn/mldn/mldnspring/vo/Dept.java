package cn.mldn.mldnspring.vo;

import java.beans.ConstructorProperties;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Dept implements Serializable {
	private Long deptno ;
	private String dname ;
	@ConstructorProperties(value= {"paramDeptno","paramDname"})
	public Dept(Long deptno,String dname) {
		this.deptno = deptno ;
		this.dname = dname ;
	}
	// setter、getter、toString()略
	public Long getDeptno() {
		return deptno;
	}
	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + "]";
	}
	
}
