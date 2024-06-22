package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.BookRatingRepository;
import com.bookRealm.api_v1.dto.BookRatingDto;
import com.bookRealm.api_v1.dto.BookRatingRequestDto;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.BookRating;
import com.bookRealm.api_v1.entity.User;

@Service
public class BookRatingService implements BookRatingInt{
	
	
	private BookRatingRepository bookRatingRepository;
	private BookService bookService;
	private UserService userService;
	
	@Autowired
	public BookRatingService(BookRatingRepository bookRatingRepository,BookService bookService,UserService userService) {
		super();
		this.bookRatingRepository = bookRatingRepository;
		this.bookService=bookService;
		this.userService=userService;
	}

	@Override
	public List<BookRating> finalAll() {
		// TODO Auto-generated method stub
		return bookRatingRepository.findAll();
	}

	@Override
	public BookRating findById(int id) {
		// TODO Auto-generated method stub
Optional<BookRating> result=bookRatingRepository.findById(id);
		
BookRating theBookRating=null;
		
		if(result.isPresent()) {
			theBookRating=result.get();
		}else {
			throw new RuntimeException("Did not find book with author id-"+id);

		}
		return theBookRating;	}

	@Override
	public BookRating save(BookRating bookRating) {
		// TODO Auto-generated method stub
		return bookRatingRepository.save(bookRating);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		bookRatingRepository.deleteById(id);
		
	}

	@Override
	public BookRating createBookRating(BookRatingRequestDto bookRatingRequestDto) {
		// TODO Auto-generated method stub
		Book book=bookService.findById(bookRatingRequestDto.getBookId());
		
		User user =userService.findById(bookRatingRequestDto.getUserId());
		
		if(book==null || user==null)
		{
			throw new RuntimeException("User or book id Not found");
		}
		
		BookRating bookRating=new BookRating();
		
		bookRating.setBook(book);
		bookRating.setRating(bookRatingRequestDto.getRating());
		bookRating.setUsers(user);
		
		
		return bookRatingRepository.save(bookRating);
	}

	@Override
	public BookRating updateBookRating(Integer id, BookRatingRequestDto bookRatingRequestDto) {
		// TODO Auto-generated method stub
		BookRating bookRating=findById(id);
		
		if(bookRating==null)
		{
			throw new RuntimeException("Book Rating with id not found="+id);
		}
		
		
		Book book=bookService.findById(bookRatingRequestDto.getBookId());
		
		User user =userService.findById(bookRatingRequestDto.getUserId());
		
		if(book==null || user==null)
		{
			throw new RuntimeException("User or book id Not found");
		}
		
		
		bookRating.setBook(book);
		bookRating.setRating(bookRatingRequestDto.getRating());
		bookRating.setUsers(user);
		
		
		return bookRatingRepository.save(bookRating);
		
	}

	@Override
	public List<BookRatingDto> getBookRatingByBookId(Integer id) {
		// TODO Auto-generated method stub
		List<BookRating> bookRatings=bookRatingRepository.findByBookId(id);
		
		if(bookRatings.isEmpty())
		{
			throw new RuntimeException("No Rating for book id found="+id);
		}
		
		double overallRating=getOverallRating(bookRatings);
		
		List<BookRatingDto> bookRatingDto=bookRatings.stream().map(br->new BookRatingDto(br.getId(),br.getRating(),br.getUsers().getUsername(),br.getBook().getTitle(),overallRating)).collect(Collectors.toList());
		
		
		return bookRatingDto;
	}
	
	
	
	 // Method to calculate overall rating
    public double getOverallRating(List<BookRating> bookRatings) {
        if (bookRatings == null || bookRatings.isEmpty()) {
            return 0.0;
        }
        OptionalDouble average = bookRatings.stream()
                                            .mapToDouble(BookRating::getRating)
                                            .average();
        double overallRating= average.orElse(0.0);
        
        return overallRating / 10;

    }

}
