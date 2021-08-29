package cn.mldn.util.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 进行分页的参数处理操作
 * @author 李兴华
 */
public class SplitPageUtil {
	private int currentPage = 1; 				// 参数：cp
	private int lineSize = 5; 					// 参数：ls
	private String keyWord; 					// 参数：kw
	private HttpServletRequest request;			// request对象

	/**
	 * 将需要进行模糊查询的columnData（下拉框）传递到组件之中，目的是为了属性操作
	 * @param handleUrl 设置分页路径的key
	 */
	public SplitPageUtil(String handleUrl) {
		this.request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		this.request.setAttribute("handleUrl", handleUrl);
		try { 		// 接收当前页码
			this.currentPage = Integer.parseInt(this.request.getParameter("cp"));
		} catch (Exception e) { }
		try { 		// 接收每页显示数据行数
			this.lineSize = Integer.parseInt(this.request.getParameter("ls"));
		} catch (Exception e) { }
		this.keyWord = this.request.getParameter("kw");	// 接收关键字
		if (this.keyWord == null) {
			this.keyWord = "";
		}
		this.request.setAttribute("currentPage", this.currentPage);
		this.request.setAttribute("lineSize", this.lineSize);
		this.request.setAttribute("keyWord", this.keyWord);
	}

	public int getCurrentPage() {					// 得到当前页码
		return currentPage;
	}
	public int getLineSize() {						// 得到每页显示数据行
		return lineSize;
	}
	public String getKeyWord() {					// 得到关键字
		return keyWord;
	}
}
