package com.bookRealm.api_v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.TagsRepository;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.Tags;
import com.bookRealm.api_v1.exception.CustomException;

@Service
public class TagsService implements TagsInt {
	
	private TagsRepository tagsRepository;
//	private BookService bookService;
	
	
	@Autowired
	public TagsService(TagsRepository tagsRepository) {
		super();
		this.tagsRepository = tagsRepository;
		//this.bookService=bookService;
	}

	@Override
	public List<Tags> finalAll() {
		// TODO Auto-generated method stub
		return tagsRepository.findAll();
	}

	@Override
	public Tags findById(int id) {
		// TODO Auto-generated method stub
		Optional<Tags> result=tagsRepository.findById(id);
		
		Tags theTags=null;
		
		if(result.isPresent()) {
			theTags=result.get();
		}else {
			throw new CustomException("Did not find tags with id-"+id);

		}
		return theTags;	
		}

	@Override
	public Tags save(Tags tags) {
		// TODO Auto-generated method stub
		return tagsRepository.save(tags);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		tagsRepository.deleteById(id);
		
	}

	@Override
	public Tags createTags(String tagName) {
		// TODO Auto-generated method stub
//		List<Book> books=new ArrayList<>();
//		
//		for(int id:bookId) {
//		Book book= bookService.findById(id);
//		if(book!=null) {
//			
//			books.add(book);
//		}
//		
//		}
//		
//		if(books.isEmpty()) {
//			throw new CustomException("Book with id not found="+bookId);
//		}
//		
		Tags tags=new Tags();
//		
//		tags.setBooks(books);
		tags.setTag_name(tagName);
		return tagsRepository.save(tags);
	}

	@Override
	public Tags updateTags(Integer id, String tagName) {
		// TODO Auto-generated method stub
		
//		List<Book> books=new ArrayList<>();
//		
//		for(int book_Id:bookId) {
//		Book book= bookService.findById(book_Id);
//		if(book!=null) {
//			
//			books.add(book);
//		}
//		
//		}
//		
//		if(books.isEmpty()) {
//			throw new CustomException("Book with id not found="+bookId);
//		}
		
		Tags tags=findById(id);
		
		//tags.setBooks(tags.getBooks());
		tags.setTag_name(tagName);
		return tagsRepository.save(tags);
	}

}
