package cn.mldn.mldnspring.util;

public class MyMath {
	private MyMath() {}
	/**
	 * 数据四舍五入处理
	 * @param num 要处理的数字
	 * @param scale 保留小数位
	 * @return 四舍五入后的数据
	 */
	public static double round(double num,int scale) {
		return Math.round(num * Math.pow(10.0, scale)) / Math.pow(10.0, scale) ;
	} 
}
