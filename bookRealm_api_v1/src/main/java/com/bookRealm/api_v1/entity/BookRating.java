package com.bookRealm.api_v1.entity;

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
	
	
	

}
