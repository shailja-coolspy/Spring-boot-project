package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.ReadEpisodes;

public interface ReadEpisodeInt {
	
List<ReadEpisodes> finalAll();
	
ReadEpisodes findById(int id);
	
ReadEpisodes save(ReadEpisodes readEpisodes);
	
	void deleteById(int id);


}
