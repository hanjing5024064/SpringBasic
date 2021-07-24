package cn.mldn.mldnspring.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dept implements Serializable {
	private Long deptno ;
	private String dname ;
	public Dept() {
		System.out.println("****** 【Dept对象实例化】 " + super.toString() + " ******");
	}
	// setter、getter略
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
		return "部门编号：" + this.deptno + "、部门名称：" + this.dname + "、对象信息：" + super.toString() ;
	}
	
}
