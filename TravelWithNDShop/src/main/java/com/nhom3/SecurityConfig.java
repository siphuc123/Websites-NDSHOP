package com.nhom3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import com.nhom3.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder pe;
	@Autowired
	UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/order/**").authenticated().antMatchers("/admin/**")
			.hasAnyRole("staf", "dire").antMatchers("/rest/authorities").hasRole("dire").anyRequest().permitAll();

		http.formLogin().loginPage("/security/login").loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error");

		http.rememberMe().tokenValiditySeconds(86400);

		// http.exceptionHandling().accessDeniedPage("/security/unauthoried");
		http.exceptionHandling().accessDeniedPage("/auth/access/denied");

		http.logout().logoutUrl("/security/logout").logoutSuccessUrl("/security/logout/success");

		http.oauth2Login().loginPage("/security/login").defaultSuccessUrl("/oauth2/login/success", true)
			.failureUrl("/security/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");

	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// cho phep truy xuat REST API tu ben ngoai
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");

		web.httpFirewall(new DefaultHttpFirewall());
	}

}
