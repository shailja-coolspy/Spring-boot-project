package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.ReadEpisodesRepository;
import com.bookRealm.api_v1.entity.ReadEpisodes;

public class ReadEpisodesService implements ReadEpisodeInt {
	
	
	private ReadEpisodesRepository readEpisodesRepository;
	
	
	@Autowired
	public ReadEpisodesService(ReadEpisodesRepository readEpisodesRepository) {
		super();
		this.readEpisodesRepository = readEpisodesRepository;
	}

	@Override
	public List<ReadEpisodes> finalAll() {
		// TODO Auto-generated method stub
		return readEpisodesRepository.findAll();
	}

	@Override
	public ReadEpisodes findById(int id) {
		// TODO Auto-generated method stub
Optional<ReadEpisodes> result=readEpisodesRepository.findById(id);
		
ReadEpisodes theReadEpisodes=null;
		
		if(result.isPresent()) {
			theReadEpisodes=result.get();
		}else {
			throw new RuntimeException("Did not find book with author id-"+id);

		}
		return theReadEpisodes;
	}

	@Override
	public ReadEpisodes save(ReadEpisodes readEpisodes) {
		// TODO Auto-generated method stub
		return readEpisodesRepository.save(readEpisodes);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		readEpisodesRepository.deleteById(id);
		
	}

}
