package com.bookRealm.api_v1.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookRealm.api_v1.dto.CastCrewRequestDto;
import com.bookRealm.api_v1.entity.CastCrew;
import com.bookRealm.api_v1.service.CastCrewService;


@RestController
@RequestMapping("/api/v1")
public class CastCrewController {
	
	private CastCrewService castCrewService;

	@Autowired
	public CastCrewController(CastCrewService castCrewService) {
		super();
		this.castCrewService = castCrewService;
	}
	
	@GetMapping("/castcrew")
	public List<CastCrew> getAllCastCrew(){
		
		return castCrewService.finalAll();
	}
	
	
	@GetMapping("/castcrew/{id}")
	public CastCrew getById(@PathVariable Integer id) {
		
		return castCrewService.findById(id);
	}
	
	@PostMapping("/castcrew")
	public CastCrew createCastCrew(@RequestBody CastCrewRequestDto castCrewRequestDto) {
		
		return castCrewService.createCastCrew(castCrewRequestDto);
	}
	
	@PutMapping("/castcrew/{id}")
	public CastCrew updateCastCrew(@PathVariable Integer id,@RequestBody CastCrewRequestDto castCrewRequestDto) {
		
		return castCrewService.updateCastCrew(id,castCrewRequestDto);
	}
	
	//NOTE::refer to categories too see how to delete when @JoinTableis not mentioned
	@DeleteMapping("/castcrew/{id}")
	public String deleteCastCrew(@PathVariable Integer id) {
		castCrewService.deleteById(id);
		
		return "Deleted cast-crew with id="+id;
	}
	

}
 