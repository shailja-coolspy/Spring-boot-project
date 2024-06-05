package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.BookReview;

public interface BookReviewInt {
	
List<BookReview> finalAll();
	
BookReview findById(int id);
	
BookReview save(BookReview bookReview);
	
	void deleteById(int id);


}
