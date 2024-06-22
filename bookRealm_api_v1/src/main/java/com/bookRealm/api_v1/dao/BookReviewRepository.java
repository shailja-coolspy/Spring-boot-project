package com.bookRealm.api_v1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookRealm.api_v1.entity.BookReview;


public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {
	
	List<BookReview> findByBookId(Integer id);


}
