package com.bookRealm.api_v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.BookRepository;
import com.bookRealm.api_v1.entity.Book;

import java.util.*;

@Service
public class BookService implements BookServiceInt {
	
	private BookRepository bookRepository;
	
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Book findById(int id) {
		// TODO Auto-generated method stub
		Optional<Book> result=bookRepository.findById(id);
		
		Book theBook=null;
		
		if(result.isPresent()) {
			theBook=result.get();
		}else {
			throw new RuntimeException("Did not find book id-"+id);
		}
		return theBook;
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		bookRepository.deleteById(id);
		
	}
	
	

}
