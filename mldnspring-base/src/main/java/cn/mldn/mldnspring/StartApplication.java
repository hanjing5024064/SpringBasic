package cn.mldn.mldnspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mldn.mldnspring.vo.Dept;
 
public class StartApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-base.xml");
//		for (int x = 0 ; x < 3 ; x ++) {
//			int temp = x ; 
//			new Thread(()->{
//				Dept dept = ctx.getBean("dept",Dept.class) ;	// 获得程序类
//				dept.setDeptno(dept.getDeptno() + temp);			// 修改部门信息
//				dept.setDname(dept.getDname() + " - " + temp);	// 修改部门信息
//				System.out.println(Thread.currentThread().getName() + "、dept = " + dept);
//			}) .start() ;
//		}
	}

}
