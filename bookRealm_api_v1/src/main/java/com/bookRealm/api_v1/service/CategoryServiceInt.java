package com.bookRealm.api_v1.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.entity.Categorie;

public interface CategoryServiceInt {
	
	List<Categorie> findAll();
	
	Categorie findById(int id);
	
	ResponseEntity<Categorie> update(Integer id,MultipartFile file,String categoryName);

	
	Categorie save(Categorie categorie);
	
	void deleteById(int id);
	
	boolean saveFile(MultipartFile file);

}
