package com.bookRealm.api_v1.entity;
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
	
	@ManyToOne
	@JoinColumn(name="author_id")
	@JsonManagedReference
	private Author author;
	
	@ManyToMany
	@JoinTable(name="bookcategories",joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
	@JsonManagedReference
	private List<Categorie> categories;
	
	
	@OneToMany(mappedBy = "book",cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<AudioEpisodes> audioEpisodes;
	
	@OneToMany(mappedBy = "book",cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<ReadEpisodes> readEpisodes;
	
	
	@ManyToMany
	@JoinTable(name="bookcrewcast",joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="cast_id"))
	@JsonManagedReference
	private List<CastCrew> castCrews;
	
	@ManyToMany
	@JoinTable(name="bookgenres",joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="genre_id"))
	@JsonManagedReference
	private List<Genres> genres;
	
	
	@ManyToMany
	@JoinTable(name="booktags",joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="tag_id"))
	@JsonManagedReference
	private List<Tags> tags;
	
	@OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BookRating> bookRatings;
	
	@OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BookReview> bookReviews;
	
	
	
	
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
	
	

	public List<AudioEpisodes> getAudioEpisodes() {
		return audioEpisodes;
	}

	public void setAudioEpisodes(List<AudioEpisodes> audioEpisodes) {
		this.audioEpisodes = audioEpisodes;
	}

	public List<ReadEpisodes> getReadEpisodes() {
		return readEpisodes;
	}

	public void setReadEpisodes(List<ReadEpisodes> readEpisodes) {
		this.readEpisodes = readEpisodes;
	}

	public List<CastCrew> getCastCrews() {
		return castCrews;
	}

	public void setCastCrews(List<CastCrew> castCrews) {
		this.castCrews = castCrews;
	}

	public List<Genres> getGenres() {
		return genres;
	}

	public void setGenres(List<Genres> genres) {
		this.genres = genres;
	}
	
	

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public List<BookRating> getBookRatings() {
		return bookRatings;
	}

	public void setBookRatings(List<BookRating> bookRatings) {
		this.bookRatings = bookRatings;
	}

	
	public List<BookReview> getBookReviews() {
		return bookReviews;
	}

	public void setBookReviews(List<BookReview> bookReviews) {
		this.bookReviews = bookReviews;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", image=" + image + ", releaseDate=" + releaseDate
				+ ", description=" + description + ", audioDuration=" + audioDuration + ", readDuration=" + readDuration
				+ ", author=" + author + ", categories=" + categories + "]";
	}
	
	
	

}
