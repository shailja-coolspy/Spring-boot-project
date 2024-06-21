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

import com.bookRealm.api_v1.dto.ProfileRequestDto;
import com.bookRealm.api_v1.entity.Profile;
import com.bookRealm.api_v1.service.ProfileService;

@RestController
@RequestMapping("/api/v1")
public class ProfileController {
	
	private ProfileService profileService;

	
	@Autowired
	public ProfileController(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}
	
	
	@GetMapping("/profiles")
	public List<Profile> getAllProfile(){
		return profileService.finalAll();
	}
	
	
	@GetMapping("/profiles/{id}")
	public Profile getProfileById(@PathVariable Integer id)
	{
		return profileService.findById(id);
	}
	
	
	@PostMapping("/profiles")
	public Profile createProfile(@RequestBody ProfileRequestDto profileRequestDto)
	{
		return profileService.createProfile(profileRequestDto);
	}
	
	@PutMapping("/profiles/{id}")
	public Profile updateProfile(@PathVariable Integer id,@RequestBody ProfileRequestDto profileRequestDto)
	{
		return profileService.updateProfile(id, profileRequestDto);
	}
	
	@DeleteMapping("/profiles/{id}")
	public String deleteProfileById(@PathVariable Integer id)
	{
		if(profileService.findById(id)==null)
		{
			throw new RuntimeException("Profile with id not found="+id);
		}
		
		profileService.deleteById(id);
		
		return "Deleted Profile with id="+id;
	}
	

}
