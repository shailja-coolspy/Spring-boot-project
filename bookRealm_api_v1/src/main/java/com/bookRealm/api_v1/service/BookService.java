package com.bookRealm.api_v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.BookRepository;
import com.bookRealm.api_v1.dao.CastCrewRepository;
import com.bookRealm.api_v1.dao.CategorieRepository;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.Categorie;

import java.util.*;

@Service
public class BookService implements BookServiceInt {
	
	private BookRepository bookRepository;
	private CategorieRepository categorieRepository;
	
	
	@Autowired
	public BookService(BookRepository bookRepository,CategorieRepository categorieRepository) {
		super();
		this.bookRepository = bookRepository;
		this.categorieRepository=categorieRepository;
	}
	
	
	//read
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

	
	//create
	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}
	
		
	
	//delete
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		bookRepository.deleteById(id);
		
	}


	//put
	@Override
	public Book update(Book book, Integer id) {
		// TODO Auto-generated method stub
		Optional<Book> result=bookRepository.findById(id);
		
		Book theBook=null;
		
		if(result.isPresent()) {
			theBook=result.get();
		}else {
			throw new RuntimeException("Did not find book id-"+id);
		}
		
		return bookRepository.save(theBook);
	}


	//Can also do::::bookRepository.findByCategoriesId(categoryId)
	//Can also do:::categorieRepository.findById(categoryId)
	@Override
	public List<Book> getBookByCategory(String categoryName) {
		// TODO Auto-generated method stub
		Categorie categorie=categorieRepository.findByCategoryName(categoryName);
		
		if(categorie!=null) {
			return categorie.getBooks();
		}else {
		return null;
	}
	
	}

}
