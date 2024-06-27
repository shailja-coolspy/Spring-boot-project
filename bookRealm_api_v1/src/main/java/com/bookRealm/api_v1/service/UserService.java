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
	
	private EmailService emailService;
	
	
	@Autowired
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,RolesService rolesService,EmailService emailService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder=passwordEncoder;
		this.rolesService=rolesService;
		this.emailService=emailService;
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
		
		String email=userRequestDto.getUserEmail();
		
		if(EmailValidationService.isEmailValid(email) || 
	            EmailValidationService.isValidDomain(email) || 
	            EmailValidationService.isEmailAddressDeliverable(email)) {
			
			emailService.sendRegistrationSuccessEmail(email, "Registration Successful", 
		            "Thank you for registering to BookRealm!");
			
		}else {
			throw new IllegalArgumentException("Invalid email address="+email);
		}
		
		user.setUserName(userRequestDto.getUserName());
		user.setUserEmail(email);
		user.setEnabled(true);
		
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
			theUser.setEnabled(true);
			
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
		
		emailService.sendRegistrationSuccessEmail(userRequestDto.getUserEmail(), "Update Successful", 
	            "Thank you for updating detail!");
		
		return userRepository.save(theUser);
	}
	
	

	@Override
	public String registerUser(UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
				
		User user=new User();
		
		String email=userRequestDto.getUserEmail();
		
		User userexist=userRepository.findByEmail(email);
		
		if(userexist!=null) {
            throw new IllegalArgumentException("Email already registered");
		}

		
		if(EmailValidationService.isEmailValid(email) || 
	            EmailValidationService.isValidDomain(email) || 
	            EmailValidationService.isEmailAddressDeliverable(email)) {
			
			user.setUserName(userRequestDto.getUserName());
			user.setUserEmail(email);
			user.setEnabled(false);
			
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
			
			userRepository.save(user);
			
			 String confirmationLink = "http://localhost:8080/api/v1/auth/confirm?email=" + email;
			
			
			emailService.sendRegistrationSuccessEmail(email, "Confirm your email", 
					"Please click the following link to confirm your email: " + confirmationLink);
			
		}else {
			throw new IllegalArgumentException("Invalid email address="+email);
		}
		
		
		return "\"Registration email sent. Please check your inbox.\"";
	}

	
	//confirm user
	@Override
	public boolean confirmUser(String email) {
		// TODO Auto-generated method stub
		 User user = userRepository.findByEmail(email);
		 
         if (user != null) {
             user.setEnabled(true);
             userRepository.save(user);
             
         	emailService.sendRegistrationSuccessEmail(email, "Registration Successful", 
		            "Thank you for registering to BookRealm!");
			
             return true;
         }
        return false; 
	}
	
	
	
//	 public static boolean isEmailValid(String email) {
//	        EmailValidator validator = EmailValidator.getInstance();
//	        return validator.isValid(email);
//	    }

}
