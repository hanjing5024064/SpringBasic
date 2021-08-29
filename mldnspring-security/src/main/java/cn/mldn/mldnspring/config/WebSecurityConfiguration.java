package cn.mldn.mldnspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}hello").roles("USER","ADMIN") ;
		auth.inMemoryAuthentication().withUser("mldn").password("{noop}java").roles("USER") ;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/pages/message/**").access("hasRole('ADMIN')").and().exceptionHandling().accessDeniedPage("/WEB-INF/pages/error_page_403.jsp") ;
	}
}
