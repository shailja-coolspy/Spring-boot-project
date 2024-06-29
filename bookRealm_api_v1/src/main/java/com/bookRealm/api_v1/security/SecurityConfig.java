package com.bookRealm.api_v1.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.servlet.DispatcherType;


@Configuration
public class SecurityConfig {
	
	private CustomUserDetailService customUserDetailService;
	
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	@Autowired
	public SecurityConfig(CustomUserDetailService customUserDetailService,JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.customUserDetailService = customUserDetailService;
		this.jwtAuthenticationEntryPoint=jwtAuthenticationEntryPoint;
		this.jwtAuthenticationFilter=jwtAuthenticationFilter;
	}

	@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	
	
	 
//	 @Bean
//		public UserDetailsManager userDetailsManager(DataSource dataSource) {
//	        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//			
//			 // Custom queries to support many-to-many relationship
//	        manager.setUsersByUsernameQuery("SELECT user_name, password, 1 as enabled FROM user WHERE user_name=?");
//	        manager.setAuthoritiesByUsernameQuery(
//	            "SELECT u.user_name, r.role_name AS authority " +
//	            "FROM user u " +
//	            "JOIN userrole ur ON u.user_id = ur.user_id " +
//	            "JOIN roles r ON ur.role_id = r.role_id " +
//	            "WHERE u.user_name=?"
//	        );
//
//	        return manager;
//		}
//	 
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http.authorizeHttpRequests(configurer ->
	                    configurer
	                    .requestMatchers(HttpMethod.POST,"/api/v1/auth/login").permitAll()
	                    .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()
	                    .requestMatchers(HttpMethod.GET,"/api/v1/auth/confirm").permitAll()
	                    .requestMatchers(HttpMethod.GET,"/v3/api-docs").permitAll()
	                    .requestMatchers(HttpMethod.GET,"/v3/api-docs/**").permitAll()
	                    .requestMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
	                    .requestMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
	                    .requestMatchers(HttpMethod.GET,"/webjars/**").permitAll()
	                    .requestMatchers("/error").permitAll()
	                    .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
	                    
	                            .anyRequest().authenticated()
	                            );
	        
	        

	        // use HTTP Basic authentication
	        //http.httpBasic(Customizer.withDefaults());

	        // disable Cross Site Request Forgery (CSRF)
	        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
	        http.csrf(csrf -> csrf.disable())
	        .exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
	        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        
	        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        
	        http.authenticationProvider(daoAuthenticationProvider());
	        
	        return http.build();
	    }
	  
	  
	  
	  @Bean
	  public DaoAuthenticationProvider daoAuthenticationProvider() {
		  
		  DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		  
		  provider.setUserDetailsService(customUserDetailService);
		  provider.setPasswordEncoder(passwordEncoder());
		  
		  return provider;
	  }
	  
	  @Bean
	  public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)throws Exception{
		  
		  return authenticationConfiguration.getAuthenticationManager();
	  }

}
