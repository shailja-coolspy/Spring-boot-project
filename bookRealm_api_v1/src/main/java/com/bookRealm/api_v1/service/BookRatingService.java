package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.BookRatingRepository;
import com.bookRealm.api_v1.entity.BookRating;

public class BookRatingService implements BookRatingInt{
	
	
	private BookRatingRepository bookRatingRepository;
	
	@Autowired
	public BookRatingService(BookRatingRepository bookRatingRepository) {
		super();
		this.bookRatingRepository = bookRatingRepository;
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

}
