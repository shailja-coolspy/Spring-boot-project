package com.bookRealm.api_v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookRealm.api_v1.dao.BookRepository;
import com.bookRealm.api_v1.dao.CategorieRepository;
import com.bookRealm.api_v1.dto.BookRequestDto;
import com.bookRealm.api_v1.entity.Author;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.CastCrew;
import com.bookRealm.api_v1.entity.Categorie;
import com.bookRealm.api_v1.entity.Genres;
import com.bookRealm.api_v1.entity.Tags;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.utils.AppConstraints;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

@Service
public class BookService implements BookServiceInt {
	
	private BookRepository bookRepository;
	private CategorieRepository categorieRepository;
	private final ObjectMapper objectMapper;
	private CategoryService categoryService;
		private CastCrewService  castCrewService;
	private GenresService genresService;
	private TagsService tagsService;
	private AuthorService authorService;

	@Autowired
	public BookService(BookRepository bookRepository, CategorieRepository categorieRepository,
			ObjectMapper objectMapper, CategoryService categoryService, @Lazy CastCrewService castCrewService,@Lazy GenresService genresService,
		@Lazy TagsService tagsService,AuthorService authorService) {
		super();
		this.bookRepository = bookRepository;
		this.categorieRepository = categorieRepository;
		this.objectMapper = objectMapper;
		this.categoryService = categoryService;
		this.castCrewService = castCrewService;
		this.genresService = genresService;
		this.tagsService = tagsService;
		this.authorService=authorService;
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
			throw new CustomException("Did not find book id-"+id);
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
	public Book update(Integer bid,MultipartFile file,String bookDto) {
		// TODO Auto-generated method stub

		Book book=findById(bid);
		
		if(saveFile(file)) {
			
			
			BookRequestDto bookRequestDto = null;
			try {
				bookRequestDto = objectMapper.readValue(bookDto, BookRequestDto.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<Categorie> categories=new ArrayList<>();
			List<CastCrew> castCrews=new ArrayList<>();
			List<Genres> genres=new ArrayList<>();
			List<Tags> tags=new ArrayList<>();

			//author
				
				Author author=null;
				try {
					author=authorService.findById(bookRequestDto.getAuthorId());
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
			
						
			//category
			for(int id:bookRequestDto.getCategId()) {
				
				Categorie categorie=null;
				try {
					categorie=categoryService.findById(id);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				if(categorie!=null) {
					categories.add(categorie);
				}
			}
			
			
			
			
			
			//castcrew
		for(int id:bookRequestDto.getCastCrewsId()) {
				
			CastCrew castCrew=null;
				try {
					castCrew=castCrewService.findById(id);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				if(castCrew!=null) {
					castCrews.add(castCrew);
				}
			}
		
		
		//genres
		
		for(int id:bookRequestDto.getGenresId()) {
			
			Genres genre=null;
			try {
				genre=genresService.findById(id);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			if(genre!=null) {
				genres.add(genre);
			}
		}
		
		//tags
		for(int id:bookRequestDto.getTagsId()) {
			
			Tags tag=null;
			try {
				tag=tagsService.findById(id);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			if(tag!=null) {
				tags.add(tag);
			}
		}
		
		
			
			if(categories.isEmpty()|| castCrews.isEmpty()||tags.isEmpty()||genres.isEmpty()|| author==null) {
				throw new CustomException("Please provide all detail to create book entry");
			}
			
			
			book.setTitle(bookRequestDto.getTitle());
			book.setReleaseDate(bookRequestDto.getReleaseDate());
			book.setDescription(bookRequestDto.getDescription());
			book.setAuthor(author);
			book.setAudioDuration(bookRequestDto.getAudioDuration());
			book.setReadDuration(bookRequestDto.getReadDuration());
			book.setCategories(categories);
			
			book.setCastCrews(castCrews);
			book.setGenres(genres);
			book.setTags(tags);
			book.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
			
			
			
		}
		else{
			throw new CustomException("File did not saved");
			
		}
		return bookRepository.save(book);
	
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


	@Override
	public Book createBook(String bookDto, MultipartFile file) {
		// TODO Auto-generated method stub
		
		Book book=new Book();
		
		if(saveFile(file)) {
			
			
			BookRequestDto bookRequestDto = null;
			try {
				bookRequestDto = objectMapper.readValue(bookDto, BookRequestDto.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<Categorie> categories=new ArrayList<>();
			List<CastCrew> castCrews=new ArrayList<>();
			List<Genres> genres=new ArrayList<>();
			List<Tags> tags=new ArrayList<>();

			//author
				
				Author author=null;
				try {
					author=authorService.findById(bookRequestDto.getAuthorId());
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
			
						
			//category
			for(int id:bookRequestDto.getCategId()) {
				
				Categorie categorie=null;
				try {
					categorie=categoryService.findById(id);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				if(categorie!=null) {
					categories.add(categorie);
				}
			}
			
			
			
			
			
			//castcrew
		for(int id:bookRequestDto.getCastCrewsId()) {
				
			CastCrew castCrew=null;
				try {
					castCrew=castCrewService.findById(id);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				if(castCrew!=null) {
					castCrews.add(castCrew);
				}
			}
		
		
		//genres
		
		for(int id:bookRequestDto.getGenresId()) {
			
			Genres genre=null;
			try {
				genre=genresService.findById(id);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			if(genre!=null) {
				genres.add(genre);
			}
		}
		
		//tags
		for(int id:bookRequestDto.getTagsId()) {
			
			Tags tag=null;
			try {
				tag=tagsService.findById(id);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			if(tag!=null) {
				tags.add(tag);
			}
		}
		
		
			
			if(categories.isEmpty()|| castCrews.isEmpty()||tags.isEmpty()||genres.isEmpty()|| author==null) {
				throw new CustomException("Please provide all detail to create book entry");
			}
			
			
			book.setTitle(bookRequestDto.getTitle());
			book.setReleaseDate(bookRequestDto.getReleaseDate());
			book.setDescription(bookRequestDto.getDescription());
			book.setAuthor(author);
			book.setAudioDuration(bookRequestDto.getAudioDuration());
			book.setReadDuration(bookRequestDto.getReadDuration());
			book.setCategories(categories);
			
			book.setCastCrews(castCrews);
			book.setGenres(genres);
			book.setTags(tags);
			book.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(LocalDate.now()+file.getOriginalFilename()).toUriString());
			
			
			
		}
		else{
			throw new CustomException("File did not saved");
			
		}
		return bookRepository.save(book);
	}

}
