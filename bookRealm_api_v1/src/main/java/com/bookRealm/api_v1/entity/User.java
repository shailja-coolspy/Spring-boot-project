package com.bookRealm.api_v1.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Entity
@Table(name="user")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String userPassword;
	
	@Column(name="enable")
	private int enable;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private Profile profile;
	
	@OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BookRating> bookRatings;
	
	@OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BookReview> bookReviews;
	
	 @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "userrole",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	 @JsonManagedReference
	    private List<Roles> roles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String userEmail, String userPassword,int enable, Profile profile, List<Roles> roles) {
		super();
		this.userName = userName;
		this.email = userEmail;
		this.userPassword = userPassword;
		this.profile = profile;
		this.enable=enable;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return email;
	}

	public void setUserEmail(String userEmail) {
		this.email = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	

	public List<BookRating> getBookRatings() {
		return bookRatings;
	}

	public void setBookRatings(List<BookRating> bookRatings) {
		this.bookRatings = bookRatings;
	}
	
	public void setEnabled(boolean enable) {
		// TODO Auto-generated method stub
		this.enable=enable==true?1:0;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> authorities=roles.stream().map((role)->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enable==1?true:false;
	}

	public List<BookReview> getBookReviews() {
		return bookReviews;
	}

	public void setBookReviews(List<BookReview> bookReviews) {
		this.bookReviews = bookReviews;
	}

	
	

}
