package cn.mldn.mldnspring.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELVarDemo01 {
	public static void main(String[] args) {
		String str = "#myvar1 + #myvar2";				// 定义了两个变量
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);
		EvaluationContext context = new StandardEvaluationContext(exp);
		context.setVariable("myvar1", "Hello ");		// 设置变量内容
		context.setVariable("myvar2", "World!");		// 设置变量内容
		String result = exp.getValue(context,String.class);
		System.out.println(result);
	}
}

