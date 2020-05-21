package com.mywork.practice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import com.mywork.practice.auth.user.UserServiceImp;


@Configuration
@Order(1)
public class AdminAuthConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserServiceImp userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/employees/department/**")
			.addFilter(getDigestFilter())
			.exceptionHandling()
			.authenticationEntryPoint(getDigestEntryPoint())
		.and()
			.authorizeRequests()
			.antMatchers("/employees/department/**")
			.hasRole("ADMIN");
	}
	
	public DigestAuthenticationFilter getDigestFilter() {
		DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
		filter.setUserDetailsService(userDetailsService());
		filter.setAuthenticationEntryPoint(getDigestEntryPoint());
		return filter;
	}
	
	private DigestAuthenticationEntryPoint getDigestEntryPoint() {
		DigestAuthenticationEntryPoint digest = new DigestAuthenticationEntryPoint();
		digest.setRealmName("digest-admin-realm");
		digest.setKey("kkuwoTO09");
		return digest;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
