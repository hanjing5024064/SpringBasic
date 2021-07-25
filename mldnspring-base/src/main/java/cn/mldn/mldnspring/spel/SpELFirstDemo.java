package cn.mldn.mldnspring.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELFirstDemo {
	public static void main(String[] args) {
		String str = "#[10 + 20]" ;	// 表达式字符串
		ExpressionParser parser = new SpelExpressionParser() ;		// Spring表达式解析器
		Expression exp = parser.parseExpression(str,new ParserContext() {
			@Override
			public boolean isTemplate() {
				return true ;
			}
			@Override
			public String getExpressionSuffix() {
				return "]";
			}
			@Override
			public String getExpressionPrefix() {
				return "#[";
			}
		}) ;															// 解析表达式
		EvaluationContext context = new StandardEvaluationContext() ;	// 上下文计算
		Integer result = (Integer) exp.getValue(context) ;				// 表达式计算
		System.out.println(result);	
	}
}

