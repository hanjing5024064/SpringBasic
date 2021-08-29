package cn.mldn.mldnspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)	// 启用注解配置
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 在进行用户角色配置时不再需要追加“ROLE_”前缀，系统会自动帮助用户添加
		auth.inMemoryAuthentication()										// 固定认证信息
			.withUser("admin")												// 用户名
			.password("{bcrypt}$2a$10$2y7higVhnHCn2L8//r/EVed2zi/LrQ.Y.svV.oeLqUM8xfUx5JWQC")
			.roles("USER","ADMIN") ;										// 角色，自动添加“ROLE_”前缀
		auth.inMemoryAuthentication()										// 固定认证信息
			.withUser("mldn")												// 用户名
			.password("{bcrypt}$2a$10$vjXs780rO3rF8ZAXuBL4..c9icL4JDvr3sweCIU9y/QWiYlHgbKGa")
			.roles("USER") ;												// 角色，自动添加“ROLE_”前缀
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() ;												// 禁用CSRF验证
		// 进行拦截路径的匹配地址配置与授权访问限定
		http.authorizeRequests()											// 配置认证请求
			.antMatchers("/pages/message/**").access("hasRole('ADMIN')")	// 授权访问
			.antMatchers("/welcomePage.action").authenticated()				// 认证访问
			.antMatchers("/**").permitAll() ;								// 任意访问
		// 进行http登录、注销与错误路径配置
		http.formLogin()													// 登录配置
			.usernameParameter("mid")										// 用户名参数配置
			.passwordParameter("pwd")										// 用户名参数配置
			.successForwardUrl("/welcomePage.action")						// 登录成功路径
			.loginPage("/loginPage.action")									// 登录表单页面
			.loginProcessingUrl("/mldn-login")								// 登录路径
			.failureForwardUrl("/login.action?error=true")					// 登录失败路径
			.and()															// 配置连接
			.logout()														// 注销配置
			.logoutUrl("/mldn-logout")										// 注销路径
			.logoutSuccessUrl("/logoutPage.action")							// 注销成功路径
			.deleteCookies("JSESSIONID")									// 删除Cookie
			.and()															// 配置连接
			.exceptionHandling()											// 认证错误配置
			.accessDeniedPage("/WEB-INF/pages/error_page_403.jsp") ;		// 授权错误页
	}
}
