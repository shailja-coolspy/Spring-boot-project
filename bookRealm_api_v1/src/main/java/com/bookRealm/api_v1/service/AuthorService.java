package com.bookRealm.api_v1.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookRealm.api_v1.dao.AuthorRepository;
import com.bookRealm.api_v1.dao.BookRepository;
import com.bookRealm.api_v1.entity.Author;

@Service
public class AuthorService implements AuthorServiceInt {
	
	
	
	private AuthorRepository authorRepository;
	
	
	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		super();
		this.authorRepository = authorRepository;
	}

	@Override
	public List<Author> findAll() {
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
		Author author=findById(id);
		
		if(author==null) {
			throw new RuntimeException("Did not found author with id"+id);
		}
		authorRepository.deleteById(id);
		
	}
	
	

	@Override
	public ResponseEntity<Author> update(Integer id, MultipartFile file, String authorName) {
		// TODO Auto-generated method stub
		Optional<Author> result=authorRepository.findById(id);
		
		Author theAuthor=null;
		
		if(result.isPresent()) {
			if(file.isEmpty() || !file.getContentType().equals("image/jpeg")|| authorName.isEmpty()) {
				throw new RuntimeException("Detail empty not uploaded or not jpeg file");
			}else {
				theAuthor=result.get();
				if(saveFile(file)) {
				
				theAuthor.setAuthorName(authorName);

				theAuthor.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
				
				}else {
					throw new RuntimeException("Cnanot process request-"+id);

				}
				
			}
			
		}else {
			throw new RuntimeException("Did not find book id-"+id);
		}
		
		return  ResponseEntity.ok(authorRepository.save(theAuthor));
		
		
	}
	
	

	@Override
	public boolean saveFile(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			//File uploadDir=new ClassPathResource("static/image/").getFile();
			Path uploadDir = Paths.get("public/images/");
			
			if(!Files.exists(uploadDir)) {
				Files.createDirectories(uploadDir);
			}
			
			//System.out.println("Check file uploadDir------||| "+uploadDir);
			
			
			Path path=Paths.get(uploadDir.toAbsolutePath()+File.separator+LocalDate.now()+file.getOriginalFilename());
			
			//System.out.println("Check one");
			
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			}catch (Exception e) {
				// TODO: handle exception
				
				return false;
			}
			
			return true;
		}	
}
