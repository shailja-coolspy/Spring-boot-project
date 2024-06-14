package com.bookRealm.api_v1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookRealm.api_v1.entity.ReadEpisodes;


public interface ReadEpisodesRepository extends JpaRepository<ReadEpisodes, Integer>{

	List<ReadEpisodes> findByBookId(Integer id);

}
