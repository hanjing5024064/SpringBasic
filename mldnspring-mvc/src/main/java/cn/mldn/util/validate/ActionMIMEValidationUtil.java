package cn.mldn.util.validate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
/**
 * 实现Action上传文件验证的处理规则
 * @author 李兴华 
 */
public class ActionMIMEValidationUtil {
	private Logger logger = LoggerFactory.getLogger(ActionMIMEValidationUtil.class);
	private Map<String,String> errors = new HashMap<String,String>() ;		// 保存错误信息
	private String rule ; 													// 保存验证规则
	private MultipartHttpServletRequest request ; 							// 请求对象
	private MessageSource messageSource ;
	/**
	 * 实例化Action数据验证工具类对象，在此类之中可以直接实现数据验证以及错误信息保存
	 * @param rule 要执行的数据验证规则
	 * @param request 通过该参数可以取得用户的请求参数
	 * @param messageSource 所有的消息资源的文字提示信息
	 */
	public ActionMIMEValidationUtil(String rule, HttpServletRequest request, MessageSource messageSource) {
		this.rule = rule ;
		this.messageSource = messageSource ;
		if (request instanceof DefaultMultipartHttpServletRequest) {
			this.request = (MultipartHttpServletRequest) request ;			// 包含有所有的上传信息
			this.handleValidator(); 										// 构造方法里验证
		}
	}
	/**
	 * 实现验证的具体操作，根据指定的验证规则来获取验证数据以实现各个数据的检测处理
	 */
	private void handleValidator() {
		String rulesResult [] = this.rule.split("\\|") ;					// 对全部的验证规则拆分
		for (int x = 0 ; x < rulesResult.length ; x ++) {
			try {
				Map<String, MultipartFile> fileMap = this.request.getFileMap() ;	// 接收所有的上传图片
				if (fileMap.size() > 0) {									// 确定有上传内容
					Iterator<Map.Entry<String, MultipartFile>> iter = fileMap.entrySet().iterator() ;
					while (iter.hasNext()) {								// 迭代每一个上传文件
						Map.Entry<String, MultipartFile> me = iter.next() ;
						if (me.getValue().getSize() > 0) {					// 有文件上传进行验证
							if (!this.validateMime(me.getValue().getContentType(), this.rule)) {	// 不符合规则
								this.errors.put(me.getKey(), this.messageSource.getMessage("validation.mime.msg",null,null)) ;
							}
						}
					}
				}
			} catch (Exception e) {
				this.logger.error(e.toString()); 
			}
		}
	}
	/**
	 * 如果当前传递MIME类型符合定义范围，则表示允许上传
	 * @param mime 当前传递文件的规则
	 * @param mimeRule 所有满足的验证规则
	 * @return 如果验证通过返回true，否则返回false
	 */
	private boolean validateMime(String mime,String mimeRule) {
		if (mime == null || "".equals(mime)) {						// 判断上传文件是否有MIME类型
			return false ;											// 验证失败
		}
		String rules [] = mimeRule.split("\\|") ;					// 拆分规则
		for (int x = 0 ; x < rules.length ; x ++) {					// 检测规则
			if (mime.equals(rules[x])) {
				return true ;										// 验证通过
			}
		}
		return false ;												// 验证失败
	}
	/**
	 * 获取全部的错误信息，如果没有错误则集合的长度为0
	 * @return 错误内容
	 */
	public Map<String, String> getErrors() {						// 获取错误信息
		return errors;
	}
}
