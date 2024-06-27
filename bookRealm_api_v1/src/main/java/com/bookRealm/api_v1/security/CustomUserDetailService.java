package com.bookRealm.api_v1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.UserRepository;
import com.bookRealm.api_v1.entity.User;
import com.bookRealm.api_v1.exception.CustomException;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
private UserRepository userRepository;
	
	
	
	@Autowired
	 public CustomUserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	//treating email as username
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			//loading user from database by user
			User user=userRepository.findByEmail(username);
			//User user=userRepository.findByUserName(username);

			
			if(user==null) {
				throw new CustomException("User with email-Id not found="+username);
			}
			
			return user;
		}

}
