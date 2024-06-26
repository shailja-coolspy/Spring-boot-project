package com.bookRealm.api_v1.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.dto.BookDTO;
import com.bookRealm.api_v1.dto.BookRequestDto;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.Categorie;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.service.BookService;

@RestController
@RequestMapping("/api/v1")
public class BookController {
	
	private BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@GetMapping("/books")
	public List<Book> getAllBook(@RequestParam(value="pageNumber",defaultValue = "0",required = false)Integer pageNumber){
       // return bookService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
		return bookService.findAll(pageNumber);
	}
	
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable int id) {
		Book book=bookService.findById(id);
		
		if(book==null) {
			throw new RuntimeException("Book id not found- "+id);

		}
		
		return book;
	}
	
	@PostMapping(consumes = {"multipart/form-data"},value="/books")
	public Book createBook(@RequestParam("imgFile")MultipartFile file,@RequestParam("Book") String bookRequestDto)
	{
		
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			throw new CustomException("File not uploaded or not jpeg file");
		}
		
		return bookService.createBook(bookRequestDto, file);
	}
	
	
	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable Integer id,@RequestParam("imgFile")MultipartFile file,@RequestParam("Book") String bookRequestDto) {
		
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			throw new CustomException("File not uploaded or not jpeg file");
		}
		
		return bookService.update(id,file,bookRequestDto);
		
	}
	
	@DeleteMapping("/books/{id}")
	public String deleteBook(@PathVariable int id)
	{
		Book book=bookService.findById(id);
		
		if(book==null) {
			throw new CustomException("Book id not found-"+ id);
		}
		
		bookService.deleteById(id);
		
		return "Deleted book id-" + id;
	}
	
	//get book by category
	
	@GetMapping("/books/category/{categoryName}")
	public List<Book> getBookByCategory(@PathVariable String categoryName){
		return bookService.getBookByCategory(categoryName);
	}
	
	
	
	//get book by author id
	@GetMapping("/books/author/{id}")
	public List<Book> getBookByAuthor(@PathVariable Integer id){
		return bookService.getBookByAuthor(id);
	}
	
	@GetMapping("/books/search/{keyword}")
	public List<Book> searchBookByTitle(@PathVariable String keyword)
	{
		return bookService.searchBook(keyword);
	}
	
	
//	 private BookDTO convertToDTO(Book book) {
//	        BookDTO bookDTO = new BookDTO();
//	        bookDTO.setId(book.getId());
//	        bookDTO.setTitle(book.getTitle());
//	        bookDTO.setImage(book.getImage());
//	        bookDTO.setReleaseDate(book.getReleaseDate());
//	        bookDTO.setDescription(book.getDescription());
//	        bookDTO.setAudioDuration(book.getAudioDuration());
//	        bookDTO.setReadDuration(book.getReadDuration());
//	        bookDTO.setAuthorName(book.getAuthor() != null ? book.getAuthor().getAuthorName() : null);
//	        bookDTO.setCategoryNames(book.getCategories().stream().map(Categorie::getCategoryName).collect(Collectors.toList()));
//	        return bookDTO;
//	    }
	
	
	

}
