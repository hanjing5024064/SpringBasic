package cn.mldn.mldnspring.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;

import cn.mldn.mldnspring.filter.ValidatorCodeUsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)	// 启用注解配置
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource ;											// 数据源 										
	@Autowired
	private UserDetailsService userDetailsService ;							// 用户服务
	@Autowired
	private SavedRequestAwareAuthenticationSuccessHandler successHandler ;	// 成功页
	@Autowired
	private SimpleUrlAuthenticationFailureHandler fialureHandler ;			// 失败页
	@Autowired
	private SessionInformationExpiredStrategy sessionExpiredStrategy ;		// session失效策略
	@Autowired
	private UsernamePasswordAuthenticationFilter authenticationFilter ;		// 认证过滤器
	@Autowired
	private JdbcTokenRepositoryImpl tokenRepository ;						// Token存储
	@Autowired
	private RoleHierarchy roleHierarchy ;									// 角色继承
	@Autowired
	private SecurityExpressionHandler<FilterInvocation> expressionHandler ;	// 表达式处理器
	@Autowired
	private AccessDecisionManager accessDecisionManager ;					// 投票管理器
	@Autowired
	private AuthenticationEntryPoint entryPoint ; 							// 登录终端
	@Autowired
	private LoginUrlAuthenticationEntryPoint authenticationEntryPoint ;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService) ;  				// 基于数据库认证
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() ;												// 禁用CSRF验证
		http.httpBasic().authenticationEntryPoint(this.authenticationEntryPoint) ;
		http.authorizeRequests().accessDecisionManager(this.accessDecisionManager) ;
		// 进行拦截路径的匹配地址配置与授权访问限定
		http.authorizeRequests()											// 配置认证请求
			.antMatchers("/pages/message/**").access("hasRole('USER')")	// 授权访问
			.antMatchers("/welcomePage.action").authenticated() ;				// 认证访问
		// 进行http注销与错误路径配置，登录操作将由过滤器负责完成
		http.logout()														// 注销配置
			.logoutUrl("/mldn-logout")										// 注销路径
			.logoutSuccessUrl("/logoutPage.action")							// 注销成功路径
			.deleteCookies("JSESSIONID","mldn-rememberme-cookie")			// 删除Cookie
			.and()															// 配置连接
			.exceptionHandling()											// 认证错误配置
			.authenticationEntryPoint(this.entryPoint) 
			.accessDeniedPage("/WEB-INF/pages/error_page_403.jsp") ;		// 授权错误页
		http.rememberMe()													// 开启RememberMe
			.rememberMeParameter("remember")								// 表单参数
			.key("mldn-lixinghua")											// 加密key
			.tokenValiditySeconds(2592000)									// 失效时间
			.rememberMeCookieName("mldn-rememberme-cookie")					// Cookie名称
			.tokenRepository(this.tokenRepository) ;						// 持久化RememberMe
		http.sessionManagement()											// Session管理
			.invalidSessionUrl("/loginPage.action")							// 失效路径
			.maximumSessions(1)												// 并发session
			.expiredSessionStrategy(this.sessionExpiredStrategy) ; 			// 失效策略
		http.addFilterBefore(this.authenticationFilter, 					// 追加过滤器
				UsernamePasswordAuthenticationFilter.class);
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.jsp") ; 						// 忽略的验证路径
	}
	@Bean
	public LoginUrlAuthenticationEntryPoint getEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/loginPage.action") ;
	}
	@Bean
	public UsernamePasswordAuthenticationFilter getAuthenticationFilter() throws Exception {
		ValidatorCodeUsernamePasswordAuthenticationFilter filter = new ValidatorCodeUsernamePasswordAuthenticationFilter() ;
		filter.setAuthenticationManager(super.authenticationManager());		// 认证管理器
		filter.setAuthenticationSuccessHandler(this.successHandler);		// 登录成功页面
		filter.setAuthenticationFailureHandler(this.fialureHandler);		// 登录失败页面
		filter.setFilterProcessesUrl("/mldn-login");						// 登录路径
		filter.setUsernameParameter("mid");									// 参数名称
		filter.setPasswordParameter("pwd");									// 参数名称
		return filter ;
	}
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler getSuccessHandler() {	// 认证成功处理
		SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler() ;
		handler.setDefaultTargetUrl("/welcomePage.action");					// 成功页面
		return handler ;
	}
	@Bean
	public SimpleUrlAuthenticationFailureHandler getFialureHandler() {		// 认证失败处理
		SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler() ;
		handler.setDefaultFailureUrl("/loginPage.action?error=true");		// 失败页面
		return handler ;
	}
	
	@Bean
	public JdbcTokenRepositoryImpl getTokenRepository() {					// 数据库保存Cookie 
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(this.dataSource);
		return tokenRepository;
	}
	@Bean
	public SessionInformationExpiredStrategy getSessionInformationExpiredStrategy() {
		// session失效策略，同时配置失效后的跳转路径
		return new SimpleRedirectSessionInformationExpiredStrategy("/logoffPage.action");
	}
	@Bean
	public RoleHierarchy getRoleHierarchy() {								// 角色继承配置
		RoleHierarchyImpl role = new RoleHierarchyImpl() ;					// 角色继承
		role.setHierarchy("ROLE_ADMIN > ROLE_USER"); 						// 继承关系
		return role ;
	}
	@Bean
	public AccessDecisionManager getAccessDecisionManager() {
		// 将所有要使用到的投票器设置到List集合之中
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>() ;
		decisionVoters.add(new RoleVoter()) ;								// 角色投票器
		decisionVoters.add(new AuthenticatedVoter()) ;						// 认证投票器
		decisionVoters.add(new RoleHierarchyVoter(this.roleHierarchy)) ;	// 角色继承投票器
		WebExpressionVoter webVoter = new WebExpressionVoter() ;
		webVoter.setExpressionHandler(this.expressionHandler);
		decisionVoters.add(webVoter) ;										// 表达式解析
		AffirmativeBased access = new AffirmativeBased(decisionVoters) ;	// 定义投票管理器
		return access ;
	}
	@Bean
	public SecurityExpressionHandler<FilterInvocation> getSecurityExpressionHandler() {	// 配置表达式
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler() ;
		expressionHandler.setRoleHierarchy(this.roleHierarchy);				// 设置角色继承
		return expressionHandler ;
	}
	
}
