package com.bookRealm.api_v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.UserRepository;
import com.bookRealm.api_v1.dto.UserRequestDto;
import com.bookRealm.api_v1.entity.Roles;
import com.bookRealm.api_v1.entity.User;

@Service
public class UserService implements UserInt{
	
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	private RolesService rolesService;
	
	
	@Autowired
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,RolesService rolesService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder=passwordEncoder;
		this.rolesService=rolesService;
	}

	@Override
	public List<User> finalAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		Optional<User> result=userRepository.findById(id);
		
		User theUser =null;
		
		if(result.isPresent()) {
			theUser=result.get();
		}else {
			throw new RuntimeException("Did not find user id-"+id);
		}
		return theUser;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		userRepository.deleteById(id);
		
	}

	@Override
	public User createUser(UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		User user=new User();
		
		user.setUserName(userRequestDto.getUserName());
		user.setUserEmail(userRequestDto.getUserEmail());
		
		//Encrypt User Password
		user.setUserPassword(passwordEncoder.encode(userRequestDto.getUserPassword()));
		//user.setUserPassword(userRequestDto.getUserPassword());
		
		List<Roles> roles=new ArrayList<>();
		
		if(userRequestDto.getRoles()!=null) {
		for(int roleId:userRequestDto.getRoles()) {
			
			Roles role=rolesService.findById(roleId);
			
			if(role!=null)
			{
				roles.add(role);
			}
			
		}
		}
		
		if(roles.isEmpty()|| userRequestDto.getRoles()==null)
		{
			Roles role=rolesService.findById(2);
			
			roles.add(role);
		}
		
		user.setRoles(roles);
		
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Integer id, UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		Optional<User> result=userRepository.findById(id);
		
		User theUser =null;
		
		if(result.isPresent()) {
			theUser=result.get();
			
			theUser.setUserName(userRequestDto.getUserName());
			theUser.setUserEmail(userRequestDto.getUserEmail());
			
			//Encrypt User Password
			theUser.setUserPassword(passwordEncoder.encode(userRequestDto.getUserPassword()));
			
			//theUser.setUserPassword(userRequestDto.getUserPassword());
			
			List<Roles> roles=new ArrayList<>();
			
			if(userRequestDto.getRoles()!=null) {
			for(int roleId:userRequestDto.getRoles()) {
				
				Roles role=rolesService.findById(roleId);
				
				if(role!=null)
				{
					roles.add(role);
				}
				
			}
			}
			
			if(roles.isEmpty()|| userRequestDto.getRoles()==null)
			{
				Roles role=rolesService.findById(2);
				
				roles.add(role);
			}
			
			theUser.setRoles(roles);
			

			
		}else {
			throw new RuntimeException("Did not find book id-"+id);
		}
		return userRepository.save(theUser);
	}

}
