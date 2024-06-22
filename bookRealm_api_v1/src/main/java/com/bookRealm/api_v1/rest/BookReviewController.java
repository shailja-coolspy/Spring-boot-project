package com.bookRealm.api_v1.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookRealm.api_v1.dto.BookReviewDto;
import com.bookRealm.api_v1.dto.BookReviewRequestDto;
import com.bookRealm.api_v1.entity.BookReview;
import com.bookRealm.api_v1.service.BookReviewService;

@RestController
@RequestMapping("/api/v1")
public class BookReviewController {
	
	private BookReviewService bookReviewService;

	public BookReviewController(BookReviewService bookReviewService) {
		super();
		this.bookReviewService = bookReviewService;
	}
	
	@GetMapping("/BookReview")
	public List<BookReview> getAllBookReviews()
	{
		return bookReviewService.finalAll();
	}
	
	@GetMapping("/BookReview/{id}")
	public BookReview getBookReviewById(@PathVariable Integer id)
	{
		return bookReviewService.findById(id);
	}
	
	
	@GetMapping("/BookReview/Book/{id}")
	public List<BookReviewDto> getBookRatingByBookId(@PathVariable Integer id)
	{
		return bookReviewService.getBookReviewByBookId(id);
	}
	
	@PostMapping("/BookReview")
	public BookReview createBookReview(@RequestBody BookReviewRequestDto bookReviewRequestDto)
	{
		return bookReviewService.createBookReview(bookReviewRequestDto);
	}
	
	@PutMapping("/BookReview/{id}")
	public BookReview updateBookRating(@PathVariable Integer id,@RequestBody BookReviewRequestDto bookReviewRequestDto)
	{
		return bookReviewService.updateBookReview(bookReviewRequestDto,id);
	}
	
	@DeleteMapping("/BookReview/{id}")
	public String deleteBookRatingById(@PathVariable Integer id)
	{
		if(bookReviewService.findById(id)==null)
		{
			throw new RuntimeException("Book Review with id not found="+id);
		}
		
		bookReviewService.deleteById(id);
		
		return "Book Review deleted with id ="+id;
	}
	


}
