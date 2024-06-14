package com.bookRealm.api_v1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="tags")
public class Tags {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tag_id")
	private int id;
	
	@Column(name="tag_name")
	private String tag_name;
	
	@ManyToMany
	@JoinTable(name="booktags",joinColumns = @JoinColumn(name="tag_id"),inverseJoinColumns = @JoinColumn(name="book_id"))
	@JsonBackReference
	private List<Book> books;
	
	

	public Tags() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Tags(String tag_name) {
		super();
		this.tag_name = tag_name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTag_name() {
		return tag_name;
	}


	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
	
	

}
