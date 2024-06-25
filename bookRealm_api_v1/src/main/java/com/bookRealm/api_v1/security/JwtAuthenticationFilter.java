package com.bookRealm.api_v1.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private UserDetailsService userDetailsService;
	
	private JwtTokenHelper jwtTokenHelper;
	
	
	@Autowired
	public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtTokenHelper jwtTokenHelper) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtTokenHelper = jwtTokenHelper;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Get Token
		String requestToken=request.getHeader("Authorization");
		
		System.out.println("Bearer TOken = "+requestToken);
		
		 String requestURI = request.getRequestURI();
		    String requestMethod = request.getMethod();
		    System.out.println("Request URI: " + requestURI + ", Method: " + requestMethod);
		    
		 // Skip JWT validation for specific endpoints
	        if (requestURI.equals("/api/v1/auth/login") && requestMethod.equals("POST")) {
	            // If it's a POST request to /api/v1/login, let it pass without token validation
	            filterChain.doFilter(request, response);
	            return;
	        }
		
		String username=null;
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
			
			token=requestToken.substring(7);
			
			try {
				username=jwtTokenHelper.getEmailFromToken(token);
				
				System.out.println("UserName:"+username);
				
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				throw new IllegalArgumentException("Unable to get jwt token");
			}
			catch (ExpiredJwtException e) {
				// TODO: handle exception
				
				//throw new ExpiredJwtException(null, Jwts.parser()., token, "jwt token has expired");
				
				System.out.println("Jwt Token has expired");
			}
			catch (MalformedJwtException e) {
				// TODO: handle exception
				throw new MalformedJwtException("Invalid JWT");
			}
			
		}else {
			
			throw new RuntimeException("Jwt token does not begin with bearer");
		}
		
		
		//Once we get the token,now validate
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails))
			{
				//authentication
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			else {
				throw new RuntimeException("Invalid jwt token");
			}
		}else {
			
			throw new RuntimeException("username is null or context is not null");
		}
		
		
		//will run before each api and check if our token is valid or not
		filterChain.doFilter(request, response);
		
	}

}
