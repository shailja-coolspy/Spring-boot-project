package com.bookRealm.api_v1.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.dto.GenresDto;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.Genres;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.service.GenresService;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestController
@RequestMapping("/api/v1")
public class GenreController {
	
	private GenresService genresService;

	@Autowired
	public GenreController(GenresService genresService) {
		super();
		this.genresService = genresService;
	}
	
	@GetMapping("/genres")
	public List<Genres> getAllGenres(){
		return genresService.finalAll();
	}
	
	@GetMapping("/genres/{id}")
	public Genres getGenresById(@PathVariable Integer id) {
		
		return genresService.findById(id);
	}
	
	@PostMapping("/genres")
	public Genres createGenres(@RequestParam("imagFile")MultipartFile file,@ModelAttribute GenresDto genresDto) {
		
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			throw new CustomException("File not uploaded or not jpeg file");
		}
		
		return genresService.createGenres(file, genresDto);
	}
	
	@PutMapping("/genres/{id}")
	public Genres updateGenres(@PathVariable Integer id,@RequestParam("imagFile")MultipartFile file,@ModelAttribute GenresDto genresDto) {
		
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			throw new CustomException("File not uploaded or not jpeg file");
		}
		
		return genresService.updateGenres(id,file, genresDto);
	}
	
	@DeleteMapping("/genres/{id}")
	public String deleteById(@PathVariable Integer id) {
		genresService.deleteById(id);
		
		return"Deleted Genres with id="+id;
	}
	
	//get book with particular genres
	@GetMapping("/books/genres/{id}")
	public List<Book> getBookByGenresId(@PathVariable Integer id){
		return genresService.getBookByGenresId(id);
	}
	
	

}
