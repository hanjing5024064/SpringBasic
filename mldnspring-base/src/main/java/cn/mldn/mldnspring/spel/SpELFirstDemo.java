package cn.mldn.mldnspring.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELFirstDemo {
	public static void main(String[] args) {
		String str = "(\"Hello \" + \"World !!!\").substring(6,12)" ;	// 表达式定义
		// 1、定义一个专属的表达式的解析工具
		ExpressionParser parser = new SpelExpressionParser() ;	// 定义一个Spring表达式解析器
		// 2、定义一个表达式的处理类
		Expression exp = parser.parseExpression(str) ;	// 从字符串里面解析出内容
		// 3、进行最终表达式的计算
		EvaluationContext context = new StandardEvaluationContext() ;
		// 4、通过表达式进行结果的计算
		System.out.println(exp.getValue(context));
	}
}
