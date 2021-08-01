package cn.mldn.mldn.spel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELCollectionDemo12 {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put("mldn", "魔乐科技：www.mldn.cn") ;					// 定义Map集合
		map.put("mldnjava", "魔乐科技软件训练营：www.mldnjava.cn") ;
		map.put("jixianit", "极限IT训练营：www.jixianit.com") ;
		String str = "#allData.?[#this.key.contains('mldn')]" ; 	// key查找
		ExpressionParser parser = new SpelExpressionParser(); 	// 定义一个Spring表达式解析器
		Expression exp = parser.parseExpression(str); 			// 从字符串里面解析出内容
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("allData", map);					// 设置可变集合
		Map<String,String> result = exp.getValue(context, Map.class);	// 获取集合
		System.out.println(result);
	}
}

