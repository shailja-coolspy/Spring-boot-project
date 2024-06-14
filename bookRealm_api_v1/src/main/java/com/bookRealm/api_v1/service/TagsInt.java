package com.bookRealm.api_v1.service;

import java.util.List;


import com.bookRealm.api_v1.entity.Tags;

public interface TagsInt {
	
List<Tags> finalAll();
	
Tags findById(int id);
	
Tags save(Tags tags);
	
	void deleteById(int id);
	
	Tags createTags(List<Integer> bookId,String tagName);
	
	Tags updateTags(Integer id,List<Integer> bookId,String tagName);



}
