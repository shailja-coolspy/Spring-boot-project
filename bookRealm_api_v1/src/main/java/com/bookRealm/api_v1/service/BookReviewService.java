package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.BookReviewRepository;
import com.bookRealm.api_v1.entity.BookReview;

public class BookReviewService implements BookReviewInt {
	
	
	private BookReviewRepository bookReviewRepository;
	
	
	@Autowired
	public BookReviewService(BookReviewRepository bookReviewRepository) {
		super();
		this.bookReviewRepository = bookReviewRepository;
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
			throw new RuntimeException("Did not find book with author id-"+id);

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

}
