package com.bookRealm.api_v1.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookRealm.api_v1.entity.Categorie;
import com.bookRealm.api_v1.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	
	private CategoryService categoryService;
    private final ObjectMapper objectMapper;


	@Autowired
	public CategoryController(CategoryService categoryService,ObjectMapper objectMapper) {
		super();
		this.categoryService = categoryService;
		this.objectMapper=objectMapper;
	}
	
	@GetMapping("/categorys")
	public List<Categorie> getAllBook(){
       // return bookService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
		return categoryService.findAll();
	}
	
	@GetMapping("/categorys/{id}")
	public Categorie getBookById(@PathVariable int id) {
		Categorie category=categoryService.findById(id);
		
		if(category==null) {
			throw new RuntimeException("Category id not found- "+id);

		}
		
		return category;
	}
	
	@PostMapping("/categorys")
	public ResponseEntity<Categorie> createBook(@RequestParam("category") String categoryJson,@RequestParam("imgFile")MultipartFile file)
	{
		//UPLOAD IMAGE FILE
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			throw new RuntimeException("File not uploaded or not jpeg file");
		}else {
			try {
				Categorie category = objectMapper.readValue(categoryJson, Categorie.class);

				//System.out.println("---------------------check_____________");
				
				//File uploadDir=new ClassPathResource("static/image/").getFile();
				Path uploadDir = Paths.get("public/images/");
				
				if(!Files.exists(uploadDir)) {
					Files.createDirectories(uploadDir);
				}
				
				//System.out.println("Check file uploadDir------||| "+uploadDir);
				
				
				Path path=Paths.get(uploadDir.toAbsolutePath()+File.separator+LocalDate.now()+file.getOriginalFilename());
				
				//System.out.println("Check one");
				
				Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				
				//System.out.println("Check two");
				
				category.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
				

				//save category
				return  ResponseEntity.ok(categoryService.save(category));
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			}
			
		}
		
		
		
	}
	
	
	
	
	@DeleteMapping("/categorys/{id}")
	public String deleteBook(@PathVariable int id)
	{
		Categorie category=categoryService.findById(id);
		
		if(category==null) {
			throw new RuntimeException("Category id not found-"+ id);
		}
		
		categoryService.deleteById(id);
		
		return "Deleted catergory id-" + id;
	}

}
