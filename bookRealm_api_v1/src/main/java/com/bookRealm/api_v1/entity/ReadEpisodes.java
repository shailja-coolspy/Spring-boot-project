package com.bookRealm.api_v1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="readepisodes")
public class ReadEpisodes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="readep_id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	
	@Column(name="duration")
	private Long duration;
	
	@Column(name="read_text")
	private String text;
	
	
	@Column(name="image_url")
	private String image_url;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	@JsonBackReference
	private Book book;

	public ReadEpisodes() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReadEpisodes(String title, Long duration, String text, String image_url) {
		super();
		this.title = title;
		this.duration = duration;
		this.text = text;
		this.image_url = image_url;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}



	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getImage_url() {
		return image_url;
	}


	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public Long getDuration() {
		return duration;
	}


	public void setDuration(Long duration) {
		this.duration = duration;
	}

	
	
	

}
