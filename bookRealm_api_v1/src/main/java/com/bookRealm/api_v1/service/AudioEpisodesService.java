package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.AudioEpisodesRepository;
import com.bookRealm.api_v1.entity.AudioEpisodes;

public class AudioEpisodesService implements AudioEpisodesInt {
	
	private AudioEpisodesRepository audioEpisodesRepository;
	
	
	@Autowired
	public AudioEpisodesService(AudioEpisodesRepository audioEpisodesRepository) {
		super();
		this.audioEpisodesRepository = audioEpisodesRepository;
	}

	@Override
	public List<AudioEpisodes> finalAll() {
		// TODO Auto-generated method stub
		return audioEpisodesRepository.findAll();
	}

	@Override
	public AudioEpisodes findById(int id) {
		// TODO Auto-generated method stub
		Optional<AudioEpisodes> result=audioEpisodesRepository.findById(id);
		
		AudioEpisodes theAudioEpisodes=null;
		
		if(result.isPresent()) {
			theAudioEpisodes=result.get();
		}else {
			throw new RuntimeException("Did not find book with author id-"+id);

		}
		
		return theAudioEpisodes;	

	}

	@Override
	public AudioEpisodes save(AudioEpisodes audioEpisodes) {
		// TODO Auto-generated method stub
		return audioEpisodesRepository.save(audioEpisodes);	
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		audioEpisodesRepository.deleteById(id);
		
	}

}
