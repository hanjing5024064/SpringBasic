package cn.mldn.mldn.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELVarDemo02 {
	public static void main(String[] args) {
		String str = "#myvar1 + #myvar2";				// 定义了两个变量
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);
		EvaluationContext context = new StandardEvaluationContext(exp);
		context.setVariable("myvar1", 100);		// 设置变量内容
		context.setVariable("myvar2", 200);		// 设置变量内容
		Integer result = exp.getValue(context,Integer.class);
		System.out.println(result);
	}
}

