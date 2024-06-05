package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.AudioEpisodes;


public interface AudioEpisodesInt {
	
	List<AudioEpisodes> finalAll();
	
	AudioEpisodes findById(int id);
	
	AudioEpisodes save(AudioEpisodes audioEpisodes);
	
	void deleteById(int id);


}
