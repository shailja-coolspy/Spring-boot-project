package com.bookRealm.api_v1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class BookReview {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private int id;
	
	@Column(name="review_text")
	private String text;
	
	@Column(name="review_date")
	@Temporal(TemporalType.DATE)
	private Date reviewDate;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	@JsonBackReference
	private Book book;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User users;
	
	
	

	public BookReview() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BookReview(String text, Date reviewDate) {
		super();
		this.text = text;
		this.reviewDate = reviewDate;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public Date getReviewDate() {
		return reviewDate;
	}



	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
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
