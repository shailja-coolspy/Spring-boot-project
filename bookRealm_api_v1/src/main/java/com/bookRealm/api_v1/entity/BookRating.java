package com.bookRealm.api_v1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="rating")
public class BookRating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rating_id")
	private int id;
	
	@Column(name="rating")
	private double rating;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	@JsonBackReference
	private Book book;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User users;
	

	
	public BookRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookRating(double rating) {
		super();
		this.rating = rating;
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}
	
	
	
	

}
