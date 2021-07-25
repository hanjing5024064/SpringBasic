package cn.mldn.mldnspring.spel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELCollectionDemo09 {
	public static void main(String[] args) {
		List<String> all = new ArrayList<String>() ;			// 定义集合对象
		Collections.addAll(all, "mldn","jixianit","mldnjava") ;	// 增加一组数据
		String str = "#allData.!['学习资源：' + #this]";			// 定义表达式
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(str); 
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("allData", all);					// 设置集合
		List<String> result = exp.getValue(context, List.class);	// 获取集合
		System.out.println(result);
		System.out.println(all); 
//		output:
//		[学习资源：mldn, 学习资源：jixianit, 学习资源：mldnjava]
//		[mldn, jixianit, mldnjava]
	} 
}

