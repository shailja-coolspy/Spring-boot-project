package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.GenresRepository;
import com.bookRealm.api_v1.entity.Genres;

public class GenresService implements GenresInt {
	
	private GenresRepository genresRepository;
	
	
	@Autowired
	public GenresService(GenresRepository genresRepository) {
		super();
		this.genresRepository = genresRepository;
	}

	@Override
	public List<Genres> finalAll() {
		// TODO Auto-generated method stub
		return genresRepository.findAll();
	}

	@Override
	public Genres findById(int id) {
		// TODO Auto-generated method stub
Optional<Genres> result=genresRepository.findById(id);
		
Genres theGenres=null;
		
		if(result.isPresent()) {
			theGenres=result.get();
		}else {
			throw new RuntimeException("Did not find book with author id-"+id);

		}
		return theGenres;	}

	@Override
	public Genres save(Genres genres) {
		// TODO Auto-generated method stub
		return genresRepository.save(genres);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		genresRepository.deleteById(id);
		
	}

}
