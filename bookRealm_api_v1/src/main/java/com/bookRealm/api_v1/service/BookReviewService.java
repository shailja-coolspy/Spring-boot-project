package com.bookRealm.api_v1.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.BookReviewRepository;
import com.bookRealm.api_v1.dto.BookReviewDto;
import com.bookRealm.api_v1.dto.BookReviewRequestDto;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.BookReview;
import com.bookRealm.api_v1.entity.User;
import com.bookRealm.api_v1.exception.CustomException;


@Service
public class BookReviewService implements BookReviewInt {
	
	
	private BookReviewRepository bookReviewRepository;
	
	private BookService bookService;
	
	private UserService userService;
	
	
	@Autowired
	public BookReviewService(BookReviewRepository bookReviewRepository,BookService bookService,UserService userService) {
		super();
		this.bookReviewRepository = bookReviewRepository;
		this.bookService=bookService;
		this.userService=userService;
	}

	@Override
	public List<BookReview> finalAll() {
		// TODO Auto-generated method stub
		return bookReviewRepository.findAll();
	}

	@Override
	public BookReview findById(int id) {
		// TODO Auto-generated method stub
		Optional<BookReview> result=bookReviewRepository.findById(id);
		
		BookReview theBookReview=null;
		
		if(result.isPresent()) {
			theBookReview=result.get();
		}else {
			throw new CustomException("Did not find book with author id-"+id);

		}
		return theBookReview;	
		}

	@Override
	public BookReview save(BookReview bookReview) {
		// TODO Auto-generated method stub
		return bookReviewRepository.save(bookReview);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		bookReviewRepository.deleteById(id);
		
	}

	@Override
	public BookReview createBookReview(BookReviewRequestDto bookReviewRequestDto) {
		// TODO Auto-generated method stub
		
		Book book=bookService.findById(bookReviewRequestDto.getBookId());
		
		User user =userService.findById(bookReviewRequestDto.getUserId());
		
		if(book==null || user==null)
		{
			throw new RuntimeException("User or book id Not found");
		}
		
		BookReview bookReview=new BookReview();
		
		bookReview.setBook(book);
		bookReview.setText(bookReviewRequestDto.getText());
		bookReview.setUsers(user);
		bookReview.setReviewDate(new Date());
		
		return bookReviewRepository.save(bookReview);
	}

	@Override
	public BookReview updateBookReview(BookReviewRequestDto bookReviewRequestDto, Integer id) {
		// TODO Auto-generated method stub
		BookReview bookReview=findById(id);
		
		if(bookReview==null)
		{
			throw new CustomException("Book Review with id not found="+id);
		}
		
		
		Book book=bookService.findById(bookReviewRequestDto.getBookId());
		
		User user =userService.findById(bookReviewRequestDto.getUserId());
		
		if(book==null || user==null)
		{
			throw new CustomException("User or book id Not found");
		}
		
		
		bookReview.setBook(book);
		bookReview.setText(bookReviewRequestDto.getText());
		bookReview.setUsers(user);
		bookReview.setReviewDate(new Date());
		
		return bookReviewRepository.save(bookReview);	
		
	}

	@Override
	public List<BookReviewDto> getBookReviewByBookId(Integer id) {
		// TODO Auto-generated method stub
		
		List<BookReview> bookReviews=bookReviewRepository.findByBookId(id);
		
		if(bookReviews.isEmpty())
		{
			throw new CustomException("No Review for book id found="+id);
		}
		
		
		List<BookReviewDto> bookReviewDtos=bookReviews.stream().map(br->new BookReviewDto(br.getId(),br.getBook().getTitle(),br.getUsers().getUsername(),br.getText(),br.getReviewDate())).collect(Collectors.toList());
		
		return bookReviewDtos;
	}

}
