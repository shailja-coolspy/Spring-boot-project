package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.CastCrewRepository;
import com.bookRealm.api_v1.entity.CastCrew;

public class CastCrewService implements CastCrewInt {
	
	private CastCrewRepository castCrewRepository;
	
	
	@Autowired
	public CastCrewService(CastCrewRepository castCrewRepository) {
		super();
		this.castCrewRepository = castCrewRepository;
	}

	@Override
	public List<CastCrew> finalAll() {
		// TODO Auto-generated method stub
		return castCrewRepository.findAll();	
		
	}

	@Override
	public CastCrew findById(int id) {
		// TODO Auto-generated method stub
		Optional<CastCrew> result=castCrewRepository.findById(id);
		
		CastCrew theCastCrew=null;
		
		if(result.isPresent()) {
			theCastCrew=result.get();
		}else {
			throw new RuntimeException("Did not find book with author id-"+id);

		}
		return theCastCrew;	
		
	}

	

	@Override
	public CastCrew save(CastCrew castCrew) {
		// TODO Auto-generated method stub
		return castCrewRepository.save(castCrew);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		castCrewRepository.deleteById(id);
		
	}

}
