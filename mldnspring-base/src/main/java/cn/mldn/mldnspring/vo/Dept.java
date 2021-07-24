package cn.mldn.mldnspring.vo;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class Dept implements Serializable {
	private Long deptno ;
	private String dname ;
	private Map<String,Emp> emps ;		// 描述雇员信息
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
	public void setEmps(Map<String, Emp> emps) {
		this.emps = emps;
	}
	public Map<String, Emp> getEmps() {
		return emps;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + "]";
	}
	
}
