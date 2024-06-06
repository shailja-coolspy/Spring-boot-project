package com.bookRealm.api_v1.rest;

import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookRealm.api_v1.entity.Categorie;
import com.bookRealm.api_v1.service.CategoryService;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	
	private CategoryService categoryService;
   // private final ObjectMapper objectMapper;


	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
		//this.objectMapper=objectMapper;
	}
	
	@GetMapping("/categorys")
	public List<Categorie> getAllCategory(){
       // return bookService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
		return categoryService.findAll();
	}
	
	@GetMapping("/categorys/{id}")
	public Categorie getCategoryById(@PathVariable int id) {
		Categorie category=categoryService.findById(id);
		
		if(category==null) {
			throw new RuntimeException("Category id not found- "+id);

		}
		
		return category;
	}
	
	@PostMapping("/categorys")
	public ResponseEntity<Categorie> createCategory(@RequestParam("categoryName") String categoryName,@RequestParam("imgFile")MultipartFile file)
	{
		//UPLOAD IMAGE FILE
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			throw new RuntimeException("File not uploaded or not jpeg file");
		}else {
				//Categorie category = objectMapper.readValue(categoryJson, Categorie.class);

				if(categoryService.saveFile(file)) {
					Categorie category = new Categorie();
					
				category.setCategoryName(categoryName);

				category.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
				
				
				//save category
				return  ResponseEntity.ok(categoryService.save(category));

				}else {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

				}				
			
		}
		
		
	}
	
	
	
	@PutMapping("/categorys/{id}")
	public ResponseEntity<Categorie> updateCategory(@PathVariable Integer id,@RequestParam("categoryName") String categoryName,@RequestParam("imgFile")MultipartFile file)
	{
		
		return categoryService.update(id, file,categoryName);
		
		
	}
	
	
	
	@DeleteMapping("/categorys/{id}")
	public String deleteCategory(@PathVariable int id)
	{
		
		categoryService.deleteById(id);
		
		return "Deleted catergory id-" + id;
	}

}
