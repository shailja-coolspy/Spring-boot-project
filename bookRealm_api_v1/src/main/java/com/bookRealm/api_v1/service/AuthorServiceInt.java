package com.bookRealm.api_v1.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.entity.Author;

public interface AuthorServiceInt {
	
	List<Author> findAll();
	
	Author findById(int id);
	
	ResponseEntity<Author> update(Integer id,MultipartFile file,String authorName);

	
	Author save(Author author);
	
	void deleteById(int id);
	
	boolean saveFile(MultipartFile file);



}
