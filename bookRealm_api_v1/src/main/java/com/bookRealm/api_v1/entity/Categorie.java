package com.bookRealm.api_v1.entity;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class Categorie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private int id;
	
	@Column(name="category_name")
	private String categoryName;
	
	@Column(name="image_url")
	private String imageUrl;
	
//	@ManyToMany
//	@JoinTable(name="bookcategories",joinColumns = @JoinColumn(name="category_id"),inverseJoinColumns = @JoinColumn(name="book_id"))
//	@JsonBackReference
//	private List<Book> books;

	public Categorie() {
		// TODO Auto-generated constructor stub
	}

	public Categorie(String categoryName,String imageUrl) {
		super();
		this.categoryName = categoryName;
		this.imageUrl=imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

//	public List<Book> getBooks() {
//		return books;
//	}
//
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", categoryName=" + categoryName +  "]";
	}
	
	

}
