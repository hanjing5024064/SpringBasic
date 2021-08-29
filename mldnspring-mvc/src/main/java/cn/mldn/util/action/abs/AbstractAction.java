package cn.mldn.util.action.abs;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
/**
 * 定义公共的Action抽象类，定义Action可重用方法
 * @author 李兴华
 */
public abstract class AbstractAction {						// 该类要被子类继承
	@Autowired
	private MessageSource messageSource ;					// 资源读取

	public String getMessage(String key,Object ... param) {	// 获取资源数据
		return this.messageSource.getMessage(key, param, Locale.getDefault()) ;
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) {			// 设置Web数据绑定转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;	// 定义转换处理
		// 在WEB数据绑定之中注册一个自定义的规则绑定器，该操作主要是对java.util.Date类型处理，允许为null
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}
}
