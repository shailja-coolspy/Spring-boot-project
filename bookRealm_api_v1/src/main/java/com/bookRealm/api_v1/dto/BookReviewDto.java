package com.bookRealm.api_v1.dto;

import java.util.Date;

public class BookReviewDto {
	
	private int id;
	
	private String bookName;
	
	private String userName;
	
	private String text;
	
	private Date date;

	public BookReviewDto(int id, String bookName, String userName, String text, Date date) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.userName = userName;
		this.text = text;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
