package com.bookRealm.api_v1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookRealm.api_v1.entity.AudioEpisodes;

public interface AudioEpisodesRepository extends JpaRepository<AudioEpisodes, Integer> {
	
	
	public List<AudioEpisodes> findByBookId(Integer id);

}
