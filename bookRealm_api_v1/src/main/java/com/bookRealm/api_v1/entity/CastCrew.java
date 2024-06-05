package com.bookRealm.api_v1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	
	
	

}
