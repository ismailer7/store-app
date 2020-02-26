package com.ur.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ur.service.impl.MyUserDetailsService;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
		
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	/*
	 * '/' permit for every one
	 *  '/place/**' + /places permit for every one
	 *  '/login' permit for every one
	 *	'/register' permit for everyone
	 *	/preffered/** need permission
	 */
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disable this for post request
		http.csrf().disable();
		http.cors().and().authorizeRequests()
			.antMatchers("/something", "/preffered/**", "/add/**").hasRole("USER")
			.antMatchers("/", "/login", "/register", "/place/**", "/places").permitAll()
			.and()
			.httpBasic();
		http.headers().frameOptions().sameOrigin();
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}