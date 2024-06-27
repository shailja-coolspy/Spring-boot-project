package com.bookRealm.api_v1.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.exception.InvalidEmailException;

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
	
	private static final List<String> SKIP_URLS = Arrays.asList(
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/confirm"
    );
	
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
		
		System.out.println("Bearer Token = "+requestToken);
		
		 String requestURI = request.getRequestURI();
		    String requestMethod = request.getMethod();
		    System.out.println("Request URI: " + requestURI + ", Method: " + requestMethod);
		    
		 // Skip JWT validation for specific endpoints
		    boolean skipRequest = SKIP_URLS.stream()
	                .anyMatch(skipUrl -> (requestURI.startsWith(skipUrl) &&( requestMethod.equals("POST")|| requestMethod.equals("GET"))));

	        if (skipRequest) {
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
				throw new CustomException("Unable to get jwt token");
			}
			catch (ExpiredJwtException e) {
				// TODO: handle exception
				
				//throw new ExpiredJwtException(null, Jwts.parser()., token, "jwt token has expired");
				
				System.out.println("Jwt Token has expired");
			}
			catch (MalformedJwtException e) {
				// TODO: handle exception
				throw new CustomException("Invalid JWT");
			}
			
		}else {
			
			throw new CustomException("Jwt token does not begin with bearer");
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
				throw new CustomException("Invalid jwt token");
			}
		}else {
			
			throw new CustomException("username is null or context is not null");
		}
		
		
		//will run before each api and check if our token is valid or not
		filterChain.doFilter(request, response);
		
	}

}
