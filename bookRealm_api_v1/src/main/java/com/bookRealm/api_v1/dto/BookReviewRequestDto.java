package com.bookRealm.api_v1.dto;


public class BookReviewRequestDto {
	
	private int bookId;
	
	private int userId;
	
	private String text;
	

	public BookReviewRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookReviewRequestDto(int bookId, int userId, String text) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.text = text;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

		

}
