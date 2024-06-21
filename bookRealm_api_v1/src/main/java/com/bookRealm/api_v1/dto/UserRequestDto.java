package com.bookRealm.api_v1.dto;


import java.util.List;

import jakarta.validation.constraints.*;

public class UserRequestDto {
	
	
	@NotBlank
	@Size(min=3,message="User Name Must be of 3 character")
	private String userName;
	
	@NotBlank
	@Email(message="Email address is not valid!!")
	private String userEmail;
	
	@NotBlank
	@Size(min=5,max=10,message="Password should be of minimum 5 charater and maximum 10 character!!")
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message="Password must contain at least one letter, one number, and one special character")
	private String userPassword;
	
	private List<Integer> roles;

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	
	



}
