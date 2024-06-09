package com.bookRealm.api_v1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="castcrew")
public class CastCrew {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cast_id")
	private int id;
	
	@Column(name="cast_name")
	private String name;
	
	@Column(name="cast_role")
	private String role;
	
	
	@ManyToMany
	@JoinTable(name="bookcrewcast",joinColumns = @JoinColumn(name="cast_id"),inverseJoinColumns = @JoinColumn(name="book_id"))
	@JsonBackReference
	private List<Book> books;
	

	public CastCrew() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CastCrew(String name, String role) {
		super();
		this.name = name;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
	
	

}
