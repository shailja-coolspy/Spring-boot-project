package com.bookRealm.api_v1.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookRealm.api_v1.dao.GenresRepository;
import com.bookRealm.api_v1.dto.GenresDto;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.Genres;
import com.bookRealm.api_v1.exception.CustomException;

@Service
public class GenresService implements GenresInt {
	
	private GenresRepository genresRepository;
	//private BookService bookService;
	
	
	@Autowired
	public GenresService(GenresRepository genresRepository) {
		super();
		this.genresRepository = genresRepository;
		//this.bookService=bookService;
	}

	@Override
	public List<Genres> finalAll() {
		// TODO Auto-generated method stub
		return genresRepository.findAll();
	}

	@Override
	public Genres findById(int id) {
		// TODO Auto-generated method stub
Optional<Genres> result=genresRepository.findById(id);
		
Genres theGenres=null;
		
		if(result.isPresent()) {
			theGenres=result.get();
		}else {
			throw new CustomException("Did not find book with author id-"+id);

		}
		return theGenres;	}

	@Override
	public Genres save(Genres genres) {
		// TODO Auto-generated method stub
		return genresRepository.save(genres);
	}
	
	@Override
	public Genres createGenres(MultipartFile file, GenresDto genresDto) {
		// TODO Auto-generated method stub
		Genres genres=new Genres();
		
		if(saveFile(file)) {
			
//			List<Book> books=new ArrayList<>();
//			
//			for(int id:genresDto.getBookId()) {
//				
//				Book book=null;
//				try {
//					book=bookService.findById(id);
//				}
//				catch (Exception e) {
//					// TODO: handle exception
//				}
//				
//				if(book!=null) {
//					books.add(book);
//				}
//			}
//			
//			if(books.isEmpty()) {
//				throw new CustomException("No book with ids="+genresDto.getBookId());
//			}
			genres.setName(genresDto.getGenresName());
			genres.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
			//genres.setBooks(books);
		}
		else{
			throw new CustomException("File did not saved");
			
		}
		
		return genresRepository.save(genres);
		
	}

	@Override
	public Genres updateGenres(Integer id, MultipartFile file, GenresDto genresDto) {
		// TODO Auto-generated method stub
		Genres genres=findById(id);
		
		if(saveFile(file)) {
			
//			List<Book> books=new ArrayList<>();
//			
//			for(Integer bookId:genresDto.getBookId()) {
//				
//				Book book=null;
//				try {
//					book=bookService.findById(bookId);
//				}
//				catch (Exception e) {
//					// TODO: handle exception
//				}
//				
//				if(book!=null) {
//					books.add(book);
//				}
//			}
//			
//			if(books.isEmpty()) {
//				throw new CustomException("No book with ids="+genresDto.getBookId());
//			}
			
			genres.setName(genresDto.getGenresName());
			genres.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
			//genres.setBooks(genres.getBooks());
		}
		else{
			throw new RuntimeException("File did not saved");
			
		}
		
		return genresRepository.save(genres);
	}


	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Genres genres=findById(id);
		
		if(genres==null) {
			throw new RuntimeException("Genres with id not present="+id);
		}
		
		genresRepository.deleteById(id);
		
	}
	
	
	
	@Override
	public boolean saveFile(MultipartFile file) {
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
	public List<Book> getBookByGenresId(Integer id) {
		// TODO Auto-generated method stub
		Genres genres=findById(id);
		
		if(genres==null) {
			throw new CustomException("Genres with id not found="+id);
		}
		return genres.getBooks();
	}

	
	

}
