package com.example.kpService.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();

        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .cors()
	            .and()
            .headers().frameOptions()
            	.disable()
            	.and()
	        .csrf()
	            .disable()
	            .authorizeRequests()
	            .and()
	            .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)")
	             .and()
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	        .authorizeRequests()
	            .antMatchers("/",
			                 "/favicon.ico",
			                 "/**/*.png",
			                 "/**/*.gif",
			                 "/**/*.svg",
			                 "/**/*.jpg",
			                 "/**/*.html",
			                 "/**/*.css",
			                 "/**/*.js")
	                .permitAll()
                .antMatchers("/paymentinfo/**").permitAll()
                .antMatchers("/methodOfPayment/**").permitAll()
                .antMatchers("/merchant/**").permitAll()
                .antMatchers("/test/**").permitAll();
    }
}
