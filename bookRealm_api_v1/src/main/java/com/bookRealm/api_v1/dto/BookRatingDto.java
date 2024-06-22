package com.bookRealm.api_v1.dto;

public class BookRatingDto {
	
	private int id;
	
	private double rating;
	
	private String userName;
	
	private String bookName;
	
	private double overallrating;

	public BookRatingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookRatingDto(int id, double rating, String userName, String bookName, double overallrating) {
		super();
		this.id = id;
		this.rating = rating;
		this.userName = userName;
		this.bookName = bookName;
		this.overallrating = overallrating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getOverallrating() {
		return overallrating;
	}

	public void setOverallrating(double overallrating) {
		this.overallrating = overallrating;
	}
	
	
	
	
	

}
