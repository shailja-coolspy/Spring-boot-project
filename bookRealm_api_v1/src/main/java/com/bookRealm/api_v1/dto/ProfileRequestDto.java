package com.bookRealm.api_v1.dto;

public class ProfileRequestDto {
	
	private String about;
	
	private int userId;

	public ProfileRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfileRequestDto(String about, int userId) {
		super();
		this.about = about;
		this.userId = userId;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
