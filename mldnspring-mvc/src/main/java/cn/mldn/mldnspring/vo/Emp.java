package cn.mldn.mldnspring.vo;
import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Emp implements Serializable {
	private Long empno ;
	private String ename ;
	private Double salary ;
	private Date hiredate ;
	private Integer level ;
	private Dept dept ;
	// setter、getter、toString()略
	public Long getEmpno() {
		return empno;
	}
	public void setEmpno(Long empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", salary=" + salary + ", hiredate=" + hiredate + ", level="
				+ level + "]";
	}
	
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Dept getDept() {
		return dept;
	}
	
}
