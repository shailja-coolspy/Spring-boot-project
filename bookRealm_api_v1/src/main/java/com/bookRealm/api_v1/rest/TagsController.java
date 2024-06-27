package com.bookRealm.api_v1.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.bookRealm.api_v1.entity.Tags;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.service.TagsService;

@RestController
@RequestMapping("/api/v1")
public class TagsController {
	
	private TagsService tagsService;

	@Autowired
	public TagsController(TagsService tagsService) {
		super();
		this.tagsService = tagsService;
	}
	
	@GetMapping("/tags")
	public List<Tags> getAllTags(){
		return tagsService.finalAll();
	}
	
	@GetMapping("/tags/{id}")
	public Tags getTagsById(@PathVariable Integer id) {
		return tagsService.findById(id);
	}
	
	@PostMapping("/tags")
	public Tags createTags(@RequestParam List<Integer> bookId,@RequestParam("tagName")String tagName) {
		return tagsService.createTags(bookId, tagName);
	}
	
	@PutMapping("/tags/{id}")
	public Tags updateTags(@PathVariable Integer id,@RequestParam List<Integer> bookId,@RequestParam("tagName")String tagName) {
		
		Tags tags=tagsService.findById(id);
		
		if(tags==null) {
			throw new CustomException("Tag does not exits with id="+id);
		}
		
		return tagsService.updateTags(id,bookId, tagName);
	}
	
	@DeleteMapping("/tags/{id}")
	public String deleteTagById(@PathVariable Integer id) {
		
		Tags tags=tagsService.findById(id);
		
		if(tags==null) {
			throw new CustomException("Tag does not exits with id="+id);
		}
		
		tagsService.deleteById(id);
		
		return "Tag with id does not exits="+id;
	}

}
