package cn.mldn.mldn.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELCollectionDemo03 {
	public static void main(String[] args) {
		String str = "{'mldn','jixianit','mldnjava'}[1]";			// 定义表达式
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str); 
		EvaluationContext context = new StandardEvaluationContext();
		String result = exp.getValue(context, String.class);	// 获取集合
		System.out.println(result);
	}
}

