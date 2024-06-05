package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.CastCrew;

public interface CastCrewInt {
	
List<CastCrew> finalAll();
	
CastCrew findById(int id);
	
CastCrew save(CastCrew castCrew);
	
	void deleteById(int id);


}
