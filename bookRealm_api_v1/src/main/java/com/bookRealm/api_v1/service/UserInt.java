package com.bookRealm.api_v1.service;

import java.util.List;


import com.bookRealm.api_v1.dto.UserRequestDto;
import com.bookRealm.api_v1.entity.User;

public interface UserInt {
	
	List<User> finalAll();
	
	User findById(int id);
		
	User save(User user);

	User createUser(UserRequestDto userRequestDto);
	
	String registerUser(UserRequestDto userRequestDto);
	
	public boolean confirmUser(String email);


	User updateUser(Integer id,UserRequestDto userRequestDto);

		
		void deleteById(int id);


}
