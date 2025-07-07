package com.test.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.test.services.Impl.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity.cors().disable()
	        .csrf().disable()
	        .authorizeHttpRequests()
	        // Public URLs for Swagger
	        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
					"/swagger-resources/**", "/webjars/**", "/v1/auth/**")
			.permitAll()
	        // Public URLs for user-related API
	        .requestMatchers(HttpMethod.POST, "/api/users/**")
	        .permitAll()
//	        .requestMatchers(HttpMethod.GET,"/api/users")
//	        .permitAll()
	        // Any other request requires authentication
	        .anyRequest()
	        .authenticated()
	        .and()
	        .httpBasic();
	    return httpSecurity.build();
	    
	}
	/*
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
				.authorizeHttpRequests(requests -> requests
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
								"/swagger-resources/**", "/webjars/**", "/v1/auth/**")
						.permitAll().anyRequest().authenticated());
		return http.build();
	}*/
					

	

	
	
	

}
