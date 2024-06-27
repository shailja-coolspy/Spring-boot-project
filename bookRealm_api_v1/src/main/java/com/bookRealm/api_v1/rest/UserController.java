package com.bookRealm.api_v1.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookRealm.api_v1.dto.UserRequestDto;
import com.bookRealm.api_v1.entity.User;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		return userService.finalAll();
		
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Integer id) {
		return userService.findById(id);
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		return userService.createUser(userRequestDto);
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Integer id,@Valid @RequestBody UserRequestDto userRequestDto) {
		return userService.updateUser(id, userRequestDto);
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable Integer id)
	{
		User user=userService.findById(id);
		
		if(user==null) {
			throw new CustomException("Found no user with id ="+id);
		}
		
		userService.deleteById(id);
		
		return "Deleted User with id="+id;
	}
	

}
