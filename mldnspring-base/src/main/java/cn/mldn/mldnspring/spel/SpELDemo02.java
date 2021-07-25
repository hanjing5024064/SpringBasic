package cn.mldn.mldnspring.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpELDemo02 {
	public static void main(String[] args) {
		String exp = "1 > 2 ? 'Hello':'World'" ;
		ExpressionParser parser = new SpelExpressionParser();
		String result = parser.parseExpression(exp).getValue(String.class);
		System.out.println(result); 
	}
}
