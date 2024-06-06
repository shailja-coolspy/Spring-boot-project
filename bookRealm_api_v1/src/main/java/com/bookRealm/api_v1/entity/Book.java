package com.bookRealm.api_v1.entity;
import java.time.Duration;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="image_url")
	private String image;
	
	@Column(name="release_date")
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@Column(name="description")
	private String description;
	
	@Column(name="audio_duration")
	private Long audioDuration;
	
	@Column(name="read_duration")
	private Long readDuration;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="author_id")
	@JsonManagedReference
	private Author author;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="bookcategories",joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
	@JsonManagedReference
	private List<Categorie> categories;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String title, String image, Date releaseDate, String description, Long audioDuration,
			Long readDuration) {
		super();
		this.title = title;
		this.image = image;
		this.releaseDate = releaseDate;
		this.description = description;
		this.audioDuration = audioDuration;
		this.readDuration = readDuration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Duration getAudioDuration() {
//		return audioDuration!=null?Duration.ofSeconds(audioDuration):null;
//	}
//
//	public void setAudioDuration(Duration audioDuration) {
//		this.audioDuration = audioDuration!=null?audioDuration.getSeconds():null;
//	}

//	public Duration getReadDuration() {
//		return readDuration!=null?Duration.ofSeconds(readDuration):null;
//	}
	
	

	public Long getAudioDuration() {
		return audioDuration;
	}

	public Long getReadDuration() {
		return readDuration;
	}

	public void setReadDuration(Long readDuration) {
		this.readDuration = readDuration;
	}

	public void setAudioDuration(Long audioDuration) {
		this.audioDuration = audioDuration;
	}

//	public void setReadDuration(Duration readDuration) {
//		this.readDuration = readDuration!=null?readDuration.getSeconds():null;
//	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", image=" + image + ", releaseDate=" + releaseDate
				+ ", description=" + description + ", audioDuration=" + audioDuration + ", readDuration=" + readDuration
				+ ", author=" + author + ", categories=" + categories + "]";
	}
	
	
	

}
