package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.AuthorRepository;
import com.bookRealm.api_v1.entity.Author;

public class AuthorService implements AuthorServiceInt {
	
	
	
	private AuthorRepository authorRepository;
	
	
	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		super();
		this.authorRepository = authorRepository;
	}

	@Override
	public List<Author> finalAll() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}

	@Override
	public Author findById(int id) {
		// TODO Auto-generated method stub
		Optional<Author> result=authorRepository.findById(id);
		
		Author theAuthor=null;
		
		if(result.isPresent()) {
			theAuthor=result.get();
		}else {
			throw new RuntimeException("Did not find book with author id-"+id);

		}
		return theAuthor;
	}

	@Override
	public Author save(Author author) {
		// TODO Auto-generated method stub
		return authorRepository.save(author);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		authorRepository.deleteById(id);
		
	}
	

}
