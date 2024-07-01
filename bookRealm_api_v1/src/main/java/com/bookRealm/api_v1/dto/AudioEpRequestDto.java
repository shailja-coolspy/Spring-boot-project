package com.bookRealm.api_v1.dto;

public class AudioEpRequestDto {
	
	private String title;
	
	private Long audioDuration;
	
	private int bookId;
	
	

	public AudioEpRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AudioEpRequestDto(String title, Long audioDuration,int bookId) {
		super();
		this.title = title;
		this.audioDuration = audioDuration;
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getAudioDuration() {
		return audioDuration;
	}

	public void setAudioDuration(Long audioDuration) {
		this.audioDuration = audioDuration;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
	

}
