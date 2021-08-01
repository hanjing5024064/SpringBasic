package cn.mldn.mldn.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELClassDemo02 {
	public static void main(String[] args) {
		String str = "T(Integer).parseInt('567')";		// 调用静态方法 
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);
		EvaluationContext context = new StandardEvaluationContext(exp);
		Integer result = exp.getValue(context,Integer.class);
		System.out.println(result * 2);
	}
}

