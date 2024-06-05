package com.bookRealm.api_v1.entity;

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
	
	
	
	
	

}
