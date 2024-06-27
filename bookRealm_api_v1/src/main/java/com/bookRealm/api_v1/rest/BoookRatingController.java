package com.bookRealm.api_v1.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookRealm.api_v1.dto.BookRatingDto;
import com.bookRealm.api_v1.dto.BookRatingRequestDto;
import com.bookRealm.api_v1.entity.BookRating;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.service.BookRatingService;

@RestController
@RequestMapping("/api/v1")
public class BoookRatingController {
	
	private BookRatingService bookRatingService;

	
	@Autowired
	public BoookRatingController(BookRatingService bookRatingService) {
		super();
		this.bookRatingService = bookRatingService;
	}
	
	@GetMapping("/BookRating")
	public List<BookRating> getAllBookRating()
	{
		return bookRatingService.finalAll();
	}
	
	@GetMapping("/BookRating/{id}")
	public BookRating getBookRatingById(@PathVariable Integer id)
	{
		return bookRatingService.findById(id);
	}
	
	
	@GetMapping("/BookRating/Book/{id}")
	public List<BookRatingDto> getBookRatingByBookId(@PathVariable Integer id)
	{
		return bookRatingService.getBookRatingByBookId(id);
	}
	
	@PostMapping("/BookRating")
	public BookRating createBookRating(@RequestBody BookRatingRequestDto bookRatingRequestDto)
	{
		return bookRatingService.createBookRating(bookRatingRequestDto);
	}
	
	@PutMapping("/BookRating/{id}")
	public BookRating updateBookRating(@PathVariable Integer id,@RequestBody BookRatingRequestDto bookRatingRequestDto)
	{
		return bookRatingService.updateBookRating(id, bookRatingRequestDto);
	}
	
	@DeleteMapping("/BookRating/{id}")
	public String deleteBookRatingById(@PathVariable Integer id)
	{
		if(bookRatingService.findById(id)==null)
		{
			throw new CustomException("Book Rating with id not found="+id);
		}
		
		bookRatingService.deleteById(id);
		
		return "Book Rating deleted with id ="+id;
	}
	
	

}
