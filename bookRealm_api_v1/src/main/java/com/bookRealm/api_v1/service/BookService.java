package com.bookRealm.api_v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.BookRepository;
import com.bookRealm.api_v1.dao.CategorieRepository;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.Categorie;
import com.bookRealm.api_v1.utils.AppConstraints;

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
	public List<Book> findAll(Integer pageNumber) {
		// TODO Auto-generated method stub
		System.out.println("page number="+pageNumber+" "+"page size="+AppConstraints.PAGE_SIZE);
		Pageable pageable=PageRequest.of(pageNumber, AppConstraints.PAGE_SIZE);
		
		Page<Book> pageBook=bookRepository.findAll(pageable);
		
		List<Book> books=pageBook.getContent();
		
		return books;
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


	@Override
	public List<Book> getBookByAuthor(Integer id) {
		// TODO Auto-generated method stub
		
		List<Book> books=bookRepository.findByAuthorId(id);
		
		if(books.isEmpty()) {
			return null;
		}
		return books;
	}


	@Override
	public List<Book> searchBook(String keyword) {
		// TODO Auto-generated method stub
		return bookRepository.findByTitleContaining(keyword);
	}

}
