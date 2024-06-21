package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.ProfileReopsitory;
import com.bookRealm.api_v1.dto.ProfileRequestDto;
import com.bookRealm.api_v1.entity.Profile;
import com.bookRealm.api_v1.entity.User;


@Service
public class ProfileService implements ProfileInt {
	
	private ProfileReopsitory profileReopsitory;
	
	private UserService userService;

	@Autowired
	public ProfileService(ProfileReopsitory profileReopsitory,UserService userService) {
		super();
		this.profileReopsitory = profileReopsitory;
		this.userService=userService;
	}

	@Override
	public List<Profile> finalAll() {
		// TODO Auto-generated method stub
		return profileReopsitory.findAll();
	}

	@Override
	public Profile findById(int id) {
		// TODO Auto-generated method stub
Optional<Profile> result=profileReopsitory.findById(id);
		
Profile theProfile =null;
		
		if(result.isPresent()) {
			theProfile=result.get();
		}else {
			throw new RuntimeException("Did not find user id-"+id);
		}
		return theProfile;	
		
	}

	@Override
	public Profile save(Profile profile) {
		// TODO Auto-generated method stub
		return profileReopsitory.save(profile);
	}

	@Override
	public Profile createProfile(ProfileRequestDto profileRequestDto) {
		// TODO Auto-generated method stub
		Profile profile=new Profile();
		
		User user=userService.findById(profileRequestDto.getUserId());
		
		if(user==null)
		{
			throw new RuntimeException("No User with id found="+profileRequestDto.getUserId());
		}
		
		profile.setAbout(profileRequestDto.getAbout());
		profile.setUser(user);
		
		return profileReopsitory.save(profile);
	}

	@Override
	public Profile updateProfile(Integer id, ProfileRequestDto profileRequestDto) {
		// TODO Auto-generated method stub
		Profile profile=findById(id);
		
		if(profile==null) {
			throw new RuntimeException("No Profile with id found="+id);
		}
		
		
		User user=userService.findById(profileRequestDto.getUserId());
		
		if(user==null)
		{
			throw new RuntimeException("No User with id found="+profileRequestDto.getUserId());
		}
		
		profile.setAbout(profileRequestDto.getAbout());
		profile.setUser(user);
		
		return profileReopsitory.save(profile);	
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		profileReopsitory.deleteById(id);
		
	}

}
