package com.bookRealm.api_v1.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookRealm.api_v1.dto.CastCrewRequestDto;
import com.bookRealm.api_v1.entity.CastCrew;

public interface CastCrewInt {
	
List<CastCrew> finalAll();
	
CastCrew findById(int id);
	
CastCrew save(CastCrew castCrew);

CastCrew createCastCrew(@RequestBody CastCrewRequestDto castCrewRequestDto);

CastCrew updateCastCrew(@PathVariable Integer id,@RequestBody CastCrewRequestDto castCrewRequestDto);

	
	void deleteById(int id);


}
