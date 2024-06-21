package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.dto.ProfileRequestDto;
import com.bookRealm.api_v1.entity.Profile;

public interface ProfileInt {
	
	
List<Profile> finalAll();
	
Profile findById(int id);
		
Profile save(Profile profile);

Profile createProfile(ProfileRequestDto profileRequestDto);

Profile updateProfile(Integer id,ProfileRequestDto profileRequestDto);

		
		void deleteById(int id);


}
