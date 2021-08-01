package cn.mldn.mldn.spel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELCollectionDemo04 {
	public static void main(String[] args) {
		List<String> all = new ArrayList<String>() ;	// 定义集合对象
		Collections.addAll(all, "mldn","jixianit","mldnjava") ;	// 增加一组数据
		String str = "#allData[1]";						// 定义表达式
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str); 
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("allData", all);			// 设置集合
		String result = exp.getValue(context, String.class);	// 获取集合
		System.out.println(result);
	}
}

