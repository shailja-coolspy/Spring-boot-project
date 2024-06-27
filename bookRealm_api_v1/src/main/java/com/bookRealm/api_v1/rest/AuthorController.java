package com.bookRealm.api_v1.rest;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookRealm.api_v1.entity.Author;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.service.AuthorService;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {
	
	private AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		super();
		this.authorService = authorService;
	}
	
	
	//get author
	@GetMapping("/authors")
	public List<Author> getAllAuthor(){
		return authorService.findAll();
	}
	
	//get author by id
	@GetMapping("/authors/{id}")
	public Author getAuthorById(@PathVariable Integer id) {
		
		Author author=authorService.findById(id);
		
		if(author==null) {
			throw new  CustomException("Author with id not present"+id);
		}
		
		return author;
	}
	
	//create author
	@PostMapping("/authors")
	public ResponseEntity<Author> createAuthor(@RequestParam("authorName") String authorName,@RequestParam("imagFile")MultipartFile file) {
		
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			throw new RuntimeException("File not uploaded or not jpeg file");
		}else {

				if(authorService.saveFile(file)) {
					Author author = new Author();
					
					author.setAuthorName(authorName);

					author.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
				
				
				//save category
				return  ResponseEntity.ok(authorService.save(author));

				}else {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

				}				
			
		}

		
	}
	
	
	//update author
	@PutMapping("/authors/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable Integer id,@RequestParam("authorName") String authorName,@RequestParam("imagFile")MultipartFile file)
	{
		
		return authorService.update(id, file,authorName);
		
		
	}
	
	
	//DISCLAMER::delete author-delete author and book associated to this author as author_id can not be null
	@DeleteMapping("/authors/{id}")
	public String deleteAuthor(@PathVariable int id)
	{
		
		authorService.deleteById(id);
		
		return "Deleted catergory id-" + id;
	}
	
	
	

}
