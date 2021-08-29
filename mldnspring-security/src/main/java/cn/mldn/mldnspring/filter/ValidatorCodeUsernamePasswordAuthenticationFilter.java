package cn.mldn.mldnspring.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.google.code.kaptcha.Constants;

public class ValidatorCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
	private String codeParameter = "code" ;								// 验证码参数名称
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String captcha = (String) ((HttpServletRequest) request).getSession()
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);			// 获取生成的验证码
		String code = request.getParameter(this.codeParameter) ;		// 获取输入验证码
		String username = super.obtainUsername(request).trim() ;		// 取得用户名
		String password = super.obtainPassword(request).trim() ;		// 取得密码
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(  
                username, password); 									// 生成认证标记
		super.setDetails(request, authRequest); 						// 设置认证详情
		// 当验证码没有输入，验证码没有生成时或者验证码不匹配时会进行错误提示
		if (captcha == null || "".equals(captcha) || code == null || "".equals(code) || !captcha.equalsIgnoreCase(code)) {
			request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME", username);
			throw new AuthenticationServiceException("验证码不正确！");
		}
		return super.getAuthenticationManager().authenticate(authRequest) ;	// 交由父类做认证处理
	}
	public void setCodeParameter(String codeParameter) {
		this.codeParameter = codeParameter;
	}
}
