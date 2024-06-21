package com.bookRealm.api_v1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="profile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="profile_id")
	private int id;
	
	@Column(name="about")
	private String about;
	
	
	@OneToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;


	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Profile(String about, User user) {
		super();
		this.about = about;
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
