package com.bookRealm.api_v1.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookRealm.api_v1.dao.ReadEpisodesRepository;
import com.bookRealm.api_v1.dto.ReadEpRequestDto;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.ReadEpisodes;
import com.bookRealm.api_v1.exception.CustomException;

@Service
public class ReadEpisodesService implements ReadEpisodeInt {
	
	
	private ReadEpisodesRepository readEpisodesRepository;
	private BookService bookService;
	
	
	@Autowired
	public ReadEpisodesService(ReadEpisodesRepository readEpisodesRepository,BookService bookService) {
		super();
		this.readEpisodesRepository = readEpisodesRepository;
		this.bookService=bookService;
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
			throw new CustomException("Did not find Read episode with id-"+id);

		}
		return theReadEpisodes;
	}

	@Override
	public ReadEpisodes save(ReadEpisodes readEpisodes) {
		// TODO Auto-generated method stub
		return readEpisodesRepository.save(readEpisodes);
	}
	

	@Override
	public ReadEpisodes createReadEp(MultipartFile file, ReadEpRequestDto readEpRequestDto) {
		// TODO Auto-generated method stub
		
		if(saveImag(file)) {
			
			ReadEpisodes readEpisodes =new ReadEpisodes();
			
			
			Book book= bookService.findById(readEpRequestDto.getBookId());
			
			readEpisodes.setTitle(readEpRequestDto.getTitle());
			
			readEpisodes.setDuration(readEpRequestDto.getReadDuration());
			
			readEpisodes.setText(readEpRequestDto.getRead_text());
			
			
			
			readEpisodes.setBook(book);
			
			readEpisodes.setImage_url(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());

			return readEpisodesRepository.save(readEpisodes);
			
		}else {
		return null;
	}
	}
	
	@Override
	public ReadEpisodes updateReadEp(Integer id, MultipartFile file, ReadEpRequestDto readEpRequestDto) {
		// TODO Auto-generated method stub
		
		if(saveImag(file)) {
			ReadEpisodes readEpisodes =findById(id);
			
			Book book= bookService.findById(readEpRequestDto.getBookId());
			
			readEpisodes.setTitle(readEpRequestDto.getTitle());
			
			readEpisodes.setDuration(readEpRequestDto.getReadDuration());
			
			readEpisodes.setText(readEpRequestDto.getRead_text());
			
			readEpisodes.setBook(book);
			
			readEpisodes.setImage_url(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());

			return readEpisodesRepository.save(readEpisodes);

		}else {
		return null;
		
		}
	}


	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		if(findById(id)==null) {
			throw new CustomException("Read Episode with id not found="+id);
		}
		readEpisodesRepository.deleteById(id);
		
	}
	
	
	@Override
	public boolean saveImag(MultipartFile file) {
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
	public List<ReadEpisodes> findReadEpByBookId(Integer id) {
		// TODO Auto-generated method stub
		Book book= bookService.findById(id);
		
		if(book==null) {
			throw new CustomException("Did not find book with id-"+id);
		}
		
		

		return readEpisodesRepository.findByBookId(id);
	}

	


}
