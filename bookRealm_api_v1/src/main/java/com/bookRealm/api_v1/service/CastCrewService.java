package com.bookRealm.api_v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookRealm.api_v1.dao.CastCrewRepository;
import com.bookRealm.api_v1.dto.CastCrewRequestDto;
import com.bookRealm.api_v1.entity.Book;
import com.bookRealm.api_v1.entity.CastCrew;
import com.bookRealm.api_v1.exception.CustomException;

@Service
public class CastCrewService implements CastCrewInt {
	
	private CastCrewRepository castCrewRepository;
	//private BookService bookService;
	
	
	@Autowired
	public CastCrewService(CastCrewRepository castCrewRepository) {
		super();
		this.castCrewRepository = castCrewRepository;
		//this.bookService=bookService;
	}

	@Override
	public List<CastCrew> finalAll() {
		// TODO Auto-generated method stub
		return castCrewRepository.findAll();	
		
	}

	@Override
	public CastCrew findById(int id) {
		// TODO Auto-generated method stub
		Optional<CastCrew> result=castCrewRepository.findById(id);
		
		CastCrew theCastCrew=null;
		
		if(result.isPresent()) {
			theCastCrew=result.get();
		}else {
			throw new CustomException("Did not find book with author id-"+id);

		}
		return theCastCrew;	
		
	}

	

	@Override
	public CastCrew save(CastCrew castCrew) {
		// TODO Auto-generated method stub
		return castCrewRepository.save(castCrew);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		CastCrew castCrew=findById(id);
		
		
		if(castCrew==null) {
			
			throw new CustomException("Did not find cast crew with author id-"+id);

		}

		
		castCrewRepository.deleteById(id);
		
	}

	@Override
	public CastCrew createCastCrew(CastCrewRequestDto castCrewRequestDto) {
		// TODO Auto-generated method stub
		
		CastCrew castCrew=new CastCrew();
		
		castCrew.setName(castCrewRequestDto.getCastName());
		castCrew.setRole(castCrewRequestDto.getCastRole());
		
		//List<Book> books=new ArrayList<>();
		//List<Integer> bookIds=castCrewRequestDto.getBookId();
		
//		for(Integer bookId:bookIds) {
//			
//			Book book=null;
//			try {
//			 book=bookService.findById(bookId);
//			}
//			catch (Exception e) {
//				// TODO: handle exception
//			}
//			if(book!=null) {
//				books.add(book);
//			}
//		}
//		
//		if(books.isEmpty()) {
//			//throw new CustomException("Books with ids not found="+castCrewRequestDto.getBookId());
//			books=null;
//		}
		
		//castCrew.setBooks(books);
		
		return castCrewRepository.save(castCrew);
	}

	@Override
	public CastCrew updateCastCrew(@PathVariable Integer id,CastCrewRequestDto castCrewRequestDto) {
		// TODO Auto-generated method stub
		CastCrew castCrew=findById(id);
		
		if(castCrew==null) {
			throw new CustomException("Cast-Crew with id do not exit="+id);
		}
		
		
		castCrew.setName(castCrewRequestDto.getCastName());
		castCrew.setRole(castCrewRequestDto.getCastRole());
		
//		List<Book> books=new ArrayList<>();
//		List<Integer> bookIds=castCrewRequestDto.getBookId();
//		
//		for(Integer bookId:bookIds) {
//			
//			Book book=null;
//			try {
//			 book=bookService.findById(bookId);
//			}
//			catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			if(book!=null) {
//				books.add(book);
//			}
//		}
//		
//		if(books.isEmpty()) {
//			throw new CustomException("Books with ids not found="+castCrewRequestDto.getBookId());
//		}
//		
		//castCrew.setBooks(castCrew.getBooks());
		
		return castCrewRepository.save(castCrew);	}

}
