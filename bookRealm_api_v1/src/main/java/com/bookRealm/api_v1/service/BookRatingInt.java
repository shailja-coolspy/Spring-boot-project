package com.bookRealm.api_v1.service;

import java.util.List;


import com.bookRealm.api_v1.dto.BookRatingDto;
import com.bookRealm.api_v1.dto.BookRatingRequestDto;
import com.bookRealm.api_v1.entity.BookRating;

public interface BookRatingInt {
	
List<BookRating> finalAll();
	
BookRating findById(int id);
	
BookRating save(BookRating bookRating);

BookRating createBookRating(BookRatingRequestDto bookRatingRequestDto);

BookRating updateBookRating(Integer id, BookRatingRequestDto bookRatingRequestDto);

List<BookRatingDto> getBookRatingByBookId(Integer id);

	
void deleteById(int id);


}
