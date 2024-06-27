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

import com.bookRealm.api_v1.dao.BookRepository;
import com.bookRealm.api_v1.dao.CategorieRepository;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.Categorie;
import com.bookRealm.api_v1.exception.CustomException;

@Service
public class CategoryService implements CategoryServiceInt{
	
	private CategorieRepository categorieRepository;
	private BookRepository bookRepository;
	
	
	@Autowired
	public CategoryService(CategorieRepository categorieRepository,BookRepository bookRepository) {
		super();
		this.categorieRepository = categorieRepository;
		this.bookRepository=bookRepository;
	}

	@Override
	public List<Categorie> findAll() {
		// TODO Auto-generated method stub
		return categorieRepository.findAll();
	}

	@Override
	public Categorie findById(int id) {
		// TODO Auto-generated method stub
		Optional<Categorie> result=categorieRepository.findById(id);
		
		Categorie theCategorie=null;
		
		if(result.isPresent()) {
			theCategorie=result.get();
		}else {
			throw new CustomException("Did not find book with category id-"+id);

		}
		
		return theCategorie;
	}

	@Override
	public Categorie save(Categorie categorie) {
		// TODO Auto-generated method stub
		return categorieRepository.save(categorie);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		Optional<Categorie> result=categorieRepository.findById(id);
		
		
		Categorie theCategorie=null;
		
		if(result.isPresent()) {
			theCategorie=result.get();
		}else {
			throw new CustomException("Did not find book with category id-"+id);

		}
		
		List<Book> books=theCategorie.getBooks();
		
			if(books.isEmpty()) {
				categorieRepository.deleteById(id);

			}else {
				
				for (Book book : books) {
		            book.getCategories().remove(theCategorie);
		            bookRepository.save(book);
		        }
				
				
				categorieRepository.deleteById(id);

				
			}
		
	}
	
	
	

	@Override
	public ResponseEntity<Categorie> update(Integer id,MultipartFile file,String categoryName) {
		// TODO Auto-generated method stub
		
		Optional<Categorie> result=categorieRepository.findById(id);
		
		Categorie theCategorie=null;
		
		if(result.isPresent()) {
			//UPLOAD IMAGE FILE
			if(file.isEmpty() || !file.getContentType().equals("image/jpeg")|| categoryName.isEmpty()) {
				throw new CustomException("Detail empty not uploaded or not jpeg file");
			}else {
				//System.out.println("Check two");
				theCategorie=result.get();
				if(saveFile(file)) {
				
				theCategorie.setCategoryName(categoryName);

				theCategorie.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
				
				}else {
					throw new CustomException("Cnanot process request-"+id);

				}
				
			}
			
		}else {
			throw new CustomException("Did not find book id-"+id);
		}
		
		return  ResponseEntity.ok(categorieRepository.save(theCategorie));
	}
	
	
	//save file image

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
