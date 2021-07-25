package cn.mldn.mldnspring.resourcedemo;

import java.util.Scanner;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class FileSystemResourceDemo {
	public static void main(String[] args) throws Exception {
		ResourceLoader resourceLoader = new DefaultResourceLoader() ;
		Resource resource = resourceLoader.getResource("file:d:/mldn.txt") ; // 文件资源
		System.out.println("资源长度：" + resource.contentLength());
		Scanner scan = new Scanner(resource.getInputStream()) ;
		while (scan.hasNext()) {
			System.out.print(scan.next()); 
		}
		scan.close(); 
	}
}
