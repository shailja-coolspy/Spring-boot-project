package com.bookRealm.api_v1.dto;

import java.time.Duration;
import java.util.Date;
import java.util.List;


public class BookDTO {
	private int id;
    private String title;
    private String image;
    private Date releaseDate;
    private String description;
    private Long audioDuration;
    private Long readDuration;
    private String authorName;
    private List<String> categoryNames;
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
	public Long getAudioDuration() {
		return audioDuration;
	}
	public void setAudioDuration(Duration duration) {
		this.audioDuration = duration!=null?duration.getSeconds():null;
	}
	public Long getReadDuration() {
		return readDuration;
	}
	public void setReadDuration(Duration readDuration) {
		this.readDuration = readDuration!=null?readDuration.getSeconds():null;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public List<String> getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(List<String> categoryNames) {
		this.categoryNames = categoryNames;
	}
    
    
}
