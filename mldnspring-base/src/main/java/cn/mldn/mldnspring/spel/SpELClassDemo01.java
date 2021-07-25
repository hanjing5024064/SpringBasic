package cn.mldn.mldnspring.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELClassDemo01 {
	public static void main(String[] args) {
		String str = "T(java.lang.String)"; 	// 获取String的Class对象
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);
		EvaluationContext context = new StandardEvaluationContext(exp);
		Class<?> cls = exp.getValue(context,Class.class);
		System.out.println(cls.getName());
	}
}

