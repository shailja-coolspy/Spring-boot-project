package com.bookRealm.api_v1.dto;

import java.util.List;

public class CastCrewRequestDto {
	private String castName;
	private String castRole;
	
	//private List<Integer> bookId;

	public CastCrewRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CastCrewRequestDto(String castName, String castRole) {
		super();
		this.castName = castName;
		this.castRole = castRole;
		//this.bookId = bookId;
	}

	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	public String getCastRole() {
		return castRole;
	}

	public void setCastRole(String castRole) {
		this.castRole = castRole;
	}

//	public List<Integer> getBookId() {
//		return bookId;
//	}
//
//	public void setBookId(List<Integer> bookId) {
//		this.bookId = bookId;
//	}
	
	
}
