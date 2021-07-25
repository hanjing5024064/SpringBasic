package cn.mldn.mldnspring.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpELDemo03 {
	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		String resultA = parser.parseExpression("null ?: \"www.mldn.cn\"").getValue(String.class); 
		String resultB = parser.parseExpression("'mldn' ?: \"www.mldnjava.cn\"").getValue(String.class); 
		System.out.println(resultA);	// 为null返回表达式2
		System.out.println(resultB);	// 不为null返回表达式1
	}
}
