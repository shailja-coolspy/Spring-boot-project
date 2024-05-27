package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.Author;

public interface AuthorServiceInt {
	
	List<Author> finalAll();
	
	Author findById(int id);
	
	Author save(Author author);
	
	void deleteById(int id);


}
