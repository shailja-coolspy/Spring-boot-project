package com.bookRealm.api_v1.entity;

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
	
	
	

}
