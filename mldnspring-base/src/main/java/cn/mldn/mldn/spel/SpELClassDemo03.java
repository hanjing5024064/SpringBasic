package cn.mldn.mldn.spel;

import java.util.Date;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELClassDemo03 {
	public static void main(String[] args) {
		String str = "new java.util.Date()";	// 实例化Date对象
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);
		EvaluationContext context = new StandardEvaluationContext(exp);
		Date result = exp.getValue(context,Date.class);
		System.out.println(result);
	}
}

