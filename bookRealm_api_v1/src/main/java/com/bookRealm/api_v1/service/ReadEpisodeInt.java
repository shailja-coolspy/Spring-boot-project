package com.bookRealm.api_v1.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.dto.ReadEpRequestDto;
import com.bookRealm.api_v1.entity.ReadEpisodes;

public interface ReadEpisodeInt {
	
List<ReadEpisodes> finalAll();
	
ReadEpisodes findById(int id);
	
ReadEpisodes save(ReadEpisodes readEpisodes);
	
	void deleteById(int id);
	
	boolean saveImag(MultipartFile file);
	
	
	ReadEpisodes createReadEp(MultipartFile file,ReadEpRequestDto readEpRequestDto);
	
	ReadEpisodes updateReadEp(Integer id,MultipartFile file,ReadEpRequestDto readEpRequestDto);
	
	List<ReadEpisodes> findReadEpByBookId(@PathVariable Integer id);



}
