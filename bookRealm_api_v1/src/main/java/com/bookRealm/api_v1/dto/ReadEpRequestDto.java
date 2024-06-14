package com.bookRealm.api_v1.dto;

public class ReadEpRequestDto {
	
    private String title;
    
    private String read_text;
	
	private Long readDuration;
	
	private int bookId;

	public ReadEpRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReadEpRequestDto(String title, String read_text, Long readDuration, int bookId) {
		super();
		this.title = title;
		this.read_text = read_text;
		this.readDuration = readDuration;
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRead_text() {
		return read_text;
	}

	public void setRead_text(String read_text) {
		this.read_text = read_text;
	}

	public Long getReadDuration() {
		return readDuration;
	}

	public void setReadDuration(Long readDuration) {
		this.readDuration = readDuration;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
	
	

}
