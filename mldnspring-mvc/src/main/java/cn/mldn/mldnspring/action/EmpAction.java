package cn.mldn.mldnspring.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.mldnspring.vo.Dept;
import cn.mldn.mldnspring.vo.Emp;
@Controller										// 定义控制器
@RequestMapping("/pages/emp/*")					// 定义该类的访问父路径，与方法中的路径进行组合为完整路径
public class EmpAction {						// 自定义Action程序类
	private Logger log = LoggerFactory.getLogger(EmpAction.class) ;	// 日志记录 
	@PostMapping("/add")						// 访问的路径为“echo.action”；
	public ModelAndView add(Emp emp) { 			// 接收请求参数
		this.log.info(emp.toString());			// 信息输出	
		ModelAndView mav = new ModelAndView() ;
		mav.setViewName("emp/emp_do");
		mav.addObject("myemp", emp) ;			// 保存数据
		return mav ;							// 不进行跳转
	}
	@RequestMapping("/add_pre")
	public String addPre() {					// 数据增加前跳转
		return "emp/emp_add" ;
	}
	
	@GetMapping("/list")
	@ResponseBody 			// 使用此注解就表示返回的对象自动变为JSON对象
	public Object list() {
		List<Emp> all = new ArrayList<Emp>() ;
		for (int x = 0 ; x < 3 ; x ++) {
			Emp vo = new Emp() ;
			vo.setEmpno(7369L + x);
			vo.setEname("李兴华 - " + x);
			Dept dept = new Dept() ;
			dept.setDeptno(10L + x);
			dept.setDname("教学部 - " + x);
			vo.setDept(dept);
			all.add(vo) ;
		}
		return all ;
	}
	
	// 其它重复定义方法略
	@InitBinder 
	public void initBinder(WebDataBinder binder) {	// 设置一个Web数据的绑定转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;	// 定义转换处理
		// 在WEB数据绑定之中注册一个自定义的规则绑定器，该操作主要是对java.util.Date类型处理，允许为null
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}

}
