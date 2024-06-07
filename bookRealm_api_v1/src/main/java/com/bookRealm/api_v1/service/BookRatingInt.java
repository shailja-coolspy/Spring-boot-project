package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.BookRating;

public interface BookRatingInt {
	
List<BookRating> finalAll();
	
BookRating findById(int id);
	
BookRating save(BookRating bookRating);
	
void deleteById(int id);


}
