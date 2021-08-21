package cn.mldn.mldnspring.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Dept implements Serializable {//通过表生成主键
	@Id													// 主键列
	@TableGenerator(
			name="DEPT_GENERATOR" ,						// 定义一个主键生成器的名称
			table="table_id_generate" ,					// 负责生成主键的数据表名称
			pkColumnName="id_key" ,						// 要获取的行信息
			pkColumnValue="DEPT_ID" ,					// 获取指定一行的信息
			valueColumnName="id_value" ,				// 主键操作的内容字段
			allocationSize=1 )							// 每次增长的步长
	@GeneratedValue(
			strategy = GenerationType.TABLE, 
			generator = "DEPT_GENERATOR")				// 根据名称引用配置的主键生成器
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
}
