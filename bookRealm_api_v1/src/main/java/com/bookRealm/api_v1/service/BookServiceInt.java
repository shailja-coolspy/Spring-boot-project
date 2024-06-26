package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.Book;

public interface BookServiceInt {
	
	List<Book> findAll(Integer pageNumber);
	
	Book findById(int id);
	
	Book save(Book book);
	
	Book update(Book book,Integer id);
	
	void deleteById(int id);
	
	List<Book> getBookByCategory(String categoryName);

	List<Book> getBookByAuthor(Integer id);
	
	
	//Search Book
	List<Book> searchBook(String keyword);


}
