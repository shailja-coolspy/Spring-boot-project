package com.bookRealm.api_v1.service;

import java.io.File;
import java.nio.file.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookRealm.api_v1.dao.AudioEpisodesRepository;
import com.bookRealm.api_v1.dto.AudioEpRequestDto;
import com.bookRealm.api_v1.entity.AudioEpisodes;
import com.bookRealm.api_v1.entity.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AudioEpisodesService implements AudioEpisodesInt {
	
	private AudioEpisodesRepository audioEpisodesRepository;
	private BookService bookService;
	
	private final ObjectMapper objectMapper;
	
	
	@Autowired
	public AudioEpisodesService(AudioEpisodesRepository audioEpisodesRepository,BookService bookService, ObjectMapper objectMapper) {
		super();
		this.audioEpisodesRepository = audioEpisodesRepository;
		this.bookService=bookService;
		this.objectMapper = objectMapper;

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
	public AudioEpisodes createAudioEp(MultipartFile imagFile, MultipartFile audioFile,
			String audioEpRequestDto) {
		// TODO Auto-generated method stub

		if(saveImagFile(imagFile)&& saveAudioFile(audioFile)) {
			
			AudioEpRequestDto audioDto = null;
			try {
				audioDto = objectMapper.readValue(audioEpRequestDto, AudioEpRequestDto.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			AudioEpisodes audioEpisodes = new AudioEpisodes();
			
			//System.out.println("Find book with id:::::"+audioDto.getBookId()+" title=="+audioDto.getTitle());
			Book book= bookService.findById(audioDto.getBookId());
			
			audioEpisodes.setTitle(audioDto.getTitle());
			audioEpisodes.setAudioDuration(audioDto.getAudioDuration());
			
			audioEpisodes.setBook(book);
			
			audioEpisodes.setAudio_url(ServletUriComponentsBuilder.fromCurrentContextPath().path("/Audios/").path(LocalDate.now()+audioFile.getOriginalFilename()).toUriString());
			
			audioEpisodes.setImage_url(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+imagFile.getOriginalFilename()).toUriString());
		
		
		//save category
		return  audioEpisodesRepository.save(audioEpisodes);

		}else {
            return null;

		}				
	}
	
	
	@Override
	public AudioEpisodes updateAudioEp(Integer id, MultipartFile imagFile, MultipartFile audioFile,
			AudioEpRequestDto audioEpRequestDto) {
		// TODO Auto-generated method stub
		
		Optional<AudioEpisodes> result=audioEpisodesRepository.findById(id);
		
		AudioEpisodes audioEpisodes=result.get();
		
		Book book= bookService.findById(audioEpRequestDto.getBookId());
		
		audioEpisodes.setTitle(audioEpRequestDto.getTitle());
		audioEpisodes.setAudioDuration(audioEpRequestDto.getAudioDuration());
		
		audioEpisodes.setBook(book);
		
		audioEpisodes.setAudio_url(ServletUriComponentsBuilder.fromCurrentContextPath().path("/Audios/").path(LocalDate.now()+audioFile.getOriginalFilename()).toUriString());
		
		audioEpisodes.setImage_url(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+imagFile.getOriginalFilename()).toUriString());
	
	
	//save category
	return  audioEpisodesRepository.save(audioEpisodes);
		
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		AudioEpisodes audioEpisodes=findById(id);
		
		if(audioEpisodes==null) {
			throw new RuntimeException("Audio Episode with id not found="+id);
		}
		
		audioEpisodesRepository.deleteById(id);
		
	}
	
	
	@Override
	public List<AudioEpisodes> findAudioEpByBookId(Integer id) {
		// TODO Auto-generated method stub
		Book book= bookService.findById(id);
		
		if(book==null) {
			throw new RuntimeException("Did not find book with id-"+id);
		}
		
		

		return audioEpisodesRepository.findByBookId(id);
	}
	

	@Override
	public boolean saveImagFile(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			//File uploadDir=new ClassPathResource("static/image/").getFile();
			Path uploadDir = Paths.get("public/images/");
			
			if(!Files.exists(uploadDir)) {
				Files.createDirectories(uploadDir);
			}
			
			//System.out.println("Check file uploadDir------||| "+uploadDir);
			
			
			Path path=Paths.get(uploadDir.toAbsolutePath()+File.separator+LocalDate.now()+file.getOriginalFilename());
			
			//System.out.println("Check one");
			
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			}catch (Exception e) {
				// TODO: handle exception
				
				return false;
			}
			
			return true;
		}

	@Override
	public boolean saveAudioFile(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			//File uploadDir=new ClassPathResource("static/image/").getFile();
			Path uploadDir = Paths.get("public/Audios/");
			
			if(!Files.exists(uploadDir)) {
				Files.createDirectories(uploadDir);
			}
			
			//System.out.println("Check file uploadDir------||| "+uploadDir);
			
			
			Path path=Paths.get(uploadDir.toAbsolutePath()+File.separator+LocalDate.now()+file.getOriginalFilename());
			
			//System.out.println("Check one");
			
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			}catch (Exception e) {
				// TODO: handle exception
				
				return false;
			}
			
			return true;
			
	}

	

	

	

}
