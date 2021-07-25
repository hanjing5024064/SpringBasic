package cn.mldn.mldnspring.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELDemo01 {
	public static void main(String[] args) {
		String str = "1.1E10" ;	// 表达式字符串
		ExpressionParser parser = new SpelExpressionParser() ;		// Spring表达式解析器
		Expression exp = parser.parseExpression(str) ;				// 解析表达式
		EvaluationContext context = new StandardEvaluationContext() ;	// 上下文计算
		Double result = exp.getValue(context,Double.class) ;				// 表达式计算
		System.out.printf("%11.2f",result);			// 格式化输出	 
	}
}

