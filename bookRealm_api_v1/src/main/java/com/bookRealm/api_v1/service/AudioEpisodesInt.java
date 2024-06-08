package com.bookRealm.api_v1.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.dto.AudioEpRequestDto;
import com.bookRealm.api_v1.entity.AudioEpisodes;


public interface AudioEpisodesInt {
	
	List<AudioEpisodes> finalAll();
	
	AudioEpisodes findById(int id);
	
	AudioEpisodes save(AudioEpisodes audioEpisodes);
	
	AudioEpisodes createAudioEp(MultipartFile imagFile,MultipartFile audioFile,String audioEpRequestDto);
	
	AudioEpisodes updateAudioEp(Integer id ,MultipartFile imagFile,MultipartFile audioFile,AudioEpRequestDto audioEpRequestDto);

	
	void deleteById(int id);
	
	public List<AudioEpisodes> findAudioEpByBookId(@PathVariable Integer id);
	
	//can also be kept in other package like utility
	boolean saveImagFile(MultipartFile file);
	
	boolean saveAudioFile(MultipartFile file);

	


}
