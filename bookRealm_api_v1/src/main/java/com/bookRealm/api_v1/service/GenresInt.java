package com.bookRealm.api_v1.service;

import java.util.List;

import com.bookRealm.api_v1.entity.Genres;

public interface GenresInt {
	
List<Genres> finalAll();
	
Genres findById(int id);
	
Genres save(Genres genres);
	
	void deleteById(int id);


}
