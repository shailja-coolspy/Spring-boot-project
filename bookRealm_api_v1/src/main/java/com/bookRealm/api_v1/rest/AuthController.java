package com.bookRealm.api_v1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookRealm.api_v1.dto.JwtAuthRequest;
import com.bookRealm.api_v1.dto.JwtAuthResponse;
import com.bookRealm.api_v1.dto.UserRequestDto;
import com.bookRealm.api_v1.entity.User;
import com.bookRealm.api_v1.security.JwtTokenHelper;
import com.bookRealm.api_v1.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private JwtTokenHelper jwtTokenHelper;
	
	private UserDetailsService userDetailsService;
	
	private AuthenticationManager authenticationManager;
	
	private UserService userService;
	
	
	
	@Autowired
	public AuthController(JwtTokenHelper jwtTokenHelper, UserDetailsService userDetailsService,
			AuthenticationManager authenticationManager,UserService userService) {
		super();
		this.jwtTokenHelper = jwtTokenHelper;
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		
		this.userService=userService;
	}




	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)
	{
		authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUsername());
		
		 String token=jwtTokenHelper.generateToken(userDetails);
		 
		 JwtAuthResponse authResponse=new JwtAuthResponse();
		 
		 authResponse.setToken(token);
		 
		 return ResponseEntity.ok(authResponse);
	}

	
	//register user
	@PostMapping("/register")
	public String registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		return userService.registerUser(userRequestDto);
	}
	
	
	//confirm email
	 @GetMapping("/confirm")
	    public String confirmUser(@RequestParam String email) {
	        boolean isConfirmed = userService.confirmUser(email);
	        return isConfirmed ? "Email confirmed successfully." : "Invalid confirmation link.";
	    }



	private void authenticate(String username, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		
		try {
		authenticationManager.authenticate(authenticationToken);
		}
		catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new BadCredentialsException("Invalid UserName or password!!");
		}
	}

}
