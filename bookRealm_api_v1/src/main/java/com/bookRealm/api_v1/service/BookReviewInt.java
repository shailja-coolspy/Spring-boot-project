package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.dto.BookReviewDto;
import com.bookRealm.api_v1.dto.BookReviewRequestDto;
import com.bookRealm.api_v1.entity.BookReview;

public interface BookReviewInt {
	
List<BookReview> finalAll();
	
BookReview findById(int id);
	
BookReview save(BookReview bookReview);

BookReview createBookReview(BookReviewRequestDto bookReviewRequestDto);

BookReview updateBookReview(BookReviewRequestDto bookReviewRequestDto,Integer id);

List<BookReviewDto> getBookReviewByBookId(Integer id);
	
	void deleteById(int id);


}
