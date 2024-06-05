package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookRealm.api_v1.dao.TagsRepository;
import com.bookRealm.api_v1.entity.Tags;

public class TagsService implements TagsInt {
	
	private TagsRepository tagsRepository;
	
	
	@Autowired
	public TagsService(TagsRepository tagsRepository) {
		super();
		this.tagsRepository = tagsRepository;
	}

	@Override
	public List<Tags> finalAll() {
		// TODO Auto-generated method stub
		return tagsRepository.findAll();
	}

	@Override
	public Tags findById(int id) {
		// TODO Auto-generated method stub
Optional<Tags> result=tagsRepository.findById(id);
		
Tags theTags=null;
		
		if(result.isPresent()) {
			theTags=result.get();
		}else {
			throw new RuntimeException("Did not find book with author id-"+id);

		}
		return theTags;	
		}

	@Override
	public Tags save(Tags tags) {
		// TODO Auto-generated method stub
		return tagsRepository.save(tags);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		tagsRepository.deleteById(id);
		
	}

}
