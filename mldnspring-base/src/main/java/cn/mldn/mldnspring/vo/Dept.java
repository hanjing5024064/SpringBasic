package cn.mldn.mldnspring.vo;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("serial")
public class Dept implements Serializable {
	private Long deptno ;
	private String dname ;
	private Properties infos ;		// 描述信息
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
	public void setInfos(Properties infos) {
		this.infos = infos;
	}
	public Properties getInfos() {
		return infos;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + "]";
	}
	
}
