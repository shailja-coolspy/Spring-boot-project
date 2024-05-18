package com.firstProject.crudproduct.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProductSecurity {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails user=User.builder().username("user").password("{noop}user123").roles("USER").build();

		UserDetails admin=User.builder().username("admin").password("{noop}admin123").roles("USER","ADMIN").build();
		
		

		return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		
		  http.authorizeHttpRequests(configurer ->
          configurer
                  .requestMatchers("/products").hasRole("USER")
                  .requestMatchers("/products/create/**").hasRole("ADMIN")
                  .requestMatchers("/products/edit/**").hasRole("ADMIN")
                  .requestMatchers("/products/delete/**").hasRole("ADMIN")
                  .anyRequest().authenticated()
  ).formLogin(form ->
  form
  .loginPage("/")
  .loginProcessingUrl("/authenticateTheUser")
  .defaultSuccessUrl("/products",true)
  .permitAll()
) .logout(logout -> logout.permitAll()
        );
		
		//use HTTP basic authentication
		//http.httpBasic(Customizer.withDefaults());
		
		//disable cross site request forgery (csrf)
		//in general,not required for stateless rest apis that use post,put.delete and/or patch
		//http.csrf(csrf->csrf.disable());
		
		return http.build();
	}

}
