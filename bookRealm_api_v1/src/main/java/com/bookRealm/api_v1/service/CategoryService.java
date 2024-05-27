package com.bookRealm.api_v1.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.CategorieRepository;
import com.bookRealm.api_v1.entity.Categorie;

public class CategoryService implements CategoryServiceInt{
	
	private CategorieRepository categorieRepository;
	
	
	@Autowired
	public CategoryService(CategorieRepository categorieRepository) {
		super();
		this.categorieRepository = categorieRepository;
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
			throw new RuntimeException("Did not find book with category id-"+id);

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
		
		categorieRepository.deleteById(id);
		
	}

}
