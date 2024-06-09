package com.bookRealm.api_v1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="genres")
public class Genres {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="genre_id")
	private int id;
	
	@Column(name="genre_name")
	private String name;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@ManyToMany
	@JoinTable(name="bookgenres",joinColumns = @JoinColumn(name="genre_id"),inverseJoinColumns = @JoinColumn(name="book_id"))
	@JsonBackReference
	private List<Book> books;
	

	public Genres() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genres(String name, String imageUrl) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	

}
