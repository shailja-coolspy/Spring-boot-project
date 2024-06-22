package com.bookRealm.api_v1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookRealm.api_v1.entity.BookRating;


public interface BookRatingRepository extends JpaRepository<BookRating, Integer>{
	
	List<BookRating> findByBookId(Integer id);

}
