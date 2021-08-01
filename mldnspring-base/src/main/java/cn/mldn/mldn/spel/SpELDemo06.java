package cn.mldn.mldn.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELDemo06 {
	public static void main(String[] args) {
		String str = "'www.mldn.cn'.toUpperCase().replaceAll('MLDN','mldnjava')"; 
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);
		EvaluationContext context = new StandardEvaluationContext(exp);
		String result = exp.getValue(context,String.class);
		System.out.println(result); 

	}
}
