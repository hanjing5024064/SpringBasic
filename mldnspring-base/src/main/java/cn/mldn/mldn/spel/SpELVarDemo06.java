package cn.mldn.mldn.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELVarDemo06 {
	public static void main(String[] args) throws Exception {
		String str = "#root?.time";									// Date类中的getTime()方法
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);				// 设置了一个自定义的根变量
		EvaluationContext context = new StandardEvaluationContext();// 不设置对象
		Long result = exp.getValue(context,Long.class) ;
		System.out.println(result);
	}
}

