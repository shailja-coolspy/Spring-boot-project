package com.bookRealm.api_v1.dto;

import java.util.List;

public class GenresDto {
	
	private String genresName;
	private List<Integer> bookId;
	
	public GenresDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GenresDto(String genresName, List<Integer> bookId) {
		super();
		this.genresName = genresName;
		this.bookId = bookId;
	}
	public String getGenresName() {
		return genresName;
	}
	public void setGenresName(String genresName) {
		this.genresName = genresName;
	}
	public List<Integer> getBookId() {
		return bookId;
	}
	public void setBookId(List<Integer> bookId) {
		this.bookId = bookId;
	}
	
	
}
