package cn.mldn.mldn.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELDemo07 {
	public static void main(String[] args) {
		String str = "'999' matches '\\d{3}'"; 	// 正则运算
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);
		EvaluationContext context = new StandardEvaluationContext(exp);
		boolean result = exp.getValue(context,Boolean.class);
		System.out.println(result); 
	}
}
