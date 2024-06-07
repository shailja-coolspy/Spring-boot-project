package com.bookRealm.api_v1.entity;

import jakarta.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.micrometer.common.lang.NonNull;

@Entity
@Table(name="authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="author_id")
	private int id;
	
	@Column(name="author_name",unique = true)
	private String authorName;
	

	@Column(name="image_url")
	private String imageUrl;
	
	@OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Book> books;

	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(String authorName,String imageUrl) {
		super();
		this.authorName = authorName;
		this.imageUrl=imageUrl;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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

	@Override
	public String toString() {
		return "Author [id=" + id + ", authorName=" + authorName + ", books=" + books + "]";
	}
	
	

}
