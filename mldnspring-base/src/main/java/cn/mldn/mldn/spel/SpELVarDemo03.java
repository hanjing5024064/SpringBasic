package cn.mldn.mldn.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELVarDemo03 {
	public static void main(String[] args) {
		String str = "#root=='mldn' ? 'Hello MLDN' : '大家好'";	// 定义了两个变量 
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str);	// 设置了一个自定义的根变量
		EvaluationContext context = new StandardEvaluationContext("mldn");	// 根变量赋值
		String result = exp.getValue(context,String.class) ;
		System.out.println(result);  
	}
}

