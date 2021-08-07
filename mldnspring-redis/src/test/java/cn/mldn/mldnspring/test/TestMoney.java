package cn.mldn.mldnspring.test;

import java.util.Iterator;
import java.util.List;

import cn.mldn.mldnspring.util.MyMath;
import cn.mldn.mldnspring.util.SplitMoneyUtil;

public class TestMoney {
	public static void main(String[] args) {
		for (int x = 0 ; x < 20 ; x ++) {
			SplitMoneyUtil smu = new SplitMoneyUtil(5,1.01) ;
			List<Double> result = smu.getAllPackages() ;
			System.out.println("【第"+x+"次检测】结果：" + sum(result) + (1.01 == sum(result)) + "，红包：" + result); // 获得拆包后的数据，这个数据存放到redis
			//拆分结果正确。计算精度不准确小概率丢失0.01
		}
	} 
	public static double sum(List<Double> all) {
		int s =  0;
		Iterator<Double> it = all.iterator();
		while(it.hasNext()){
			double ss = it.next();
			s += (int)MyMath.round(ss * 100, 2);
		}
		return s / 100.00 ;
	}
}
