package com.bookRealm.api_v1.entity;

import jakarta.persistence.*;
import java.util.*;

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
	
	@OneToMany(mappedBy = "author")
	private List<Book> books;

	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(String authorName) {
		super();
		this.authorName = authorName;
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
