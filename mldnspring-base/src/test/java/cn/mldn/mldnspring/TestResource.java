package cn.mldn.mldnspring;

import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.resource.util.DefaultResourceBean;

@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 进行资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestResource {
	@Autowired
	private DefaultResourceBean resourceBean ;
	@Test
	public void testResource() throws Exception { 
		Iterator<org.springframework.core.io.Resource> iter = this.resourceBean.getResources().iterator() ;
		while (iter.hasNext()) {
			Scanner scan = new Scanner(iter.next().getInputStream()) ;
			scan.useDelimiter("\n") ;
			while (scan.hasNext()) {
				System.out.print(scan.next()); 
			}
			scan.close(); 
			System.out.println("******************************************");
		}
	} 
}
