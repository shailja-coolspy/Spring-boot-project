package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.Categorie;

public interface CategoryServiceInt {
	
	List<Categorie> findAll();
	
	Categorie findById(int id);
	
	Categorie save(Categorie categorie);
	
	void deleteById(int id);

}
