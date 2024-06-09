package com.bookRealm.api_v1.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.dto.GenresDto;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.Genres;

public interface GenresInt {
	
List<Genres> finalAll();
	
Genres findById(int id);
	
Genres save(Genres genres);

public boolean saveFile(MultipartFile file);

 Genres createGenres(MultipartFile file,GenresDto genresDto);

 Genres updateGenres(Integer id,MultipartFile file,GenresDto genresDto);
 
 public List<Book> getBookByGenresId(Integer id);
	
	void deleteById(int id);
	
	


}
