package cn.mldn.mldnspring.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Dept implements Serializable {
	private Long deptno ;
	private String dname ;
	private String[] infos ;		// 描述部门信息
	private List<Emp> emps ; 		// 部门中员工信息
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
	public void setInfos(String[] infos) {
		this.infos = infos;
	}
	public String[] getInfos() {
		return infos;
	}
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	public List<Emp> getEmps() {
		return emps;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + "]";
	}
	
}
