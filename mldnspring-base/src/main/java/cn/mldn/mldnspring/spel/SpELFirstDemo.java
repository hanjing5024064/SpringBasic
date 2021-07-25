package cn.mldn.mldnspring.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELFirstDemo {
	public static void main(String[] args) {
		String str = "(\"Hello \" + \"World !!!\").substring(#start,#end)" ;	// 表达式定义
		ExpressionParser parser = new SpelExpressionParser() ;		// Spring表达式解析器
		Expression exp = parser.parseExpression(str) ;				// 解析表达式
		EvaluationContext context = new StandardEvaluationContext() ;	// 上下文计算
		context.setVariable("start", 6); 							// 定义变量
		context.setVariable("end", 12);								// 定义变量
		System.out.println(exp.getValue(context));					// 表达式计算

	}
}
