package com.bookRealm.api_v1.dto;

public class BookRatingRequestDto {
	
	private int bookId;
	private int userId;
	
	private double rating;

	public BookRatingRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookRatingRequestDto(int bookId, int userId, double rating) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.rating = rating;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	

}
