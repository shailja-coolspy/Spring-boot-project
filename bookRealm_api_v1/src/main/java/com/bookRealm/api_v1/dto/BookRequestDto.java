package com.bookRealm.api_v1.dto;

import java.util.Date;
import java.util.List;


public class BookRequestDto {
	
	private String title;
	
	private Date releaseDate;
	
	private String description;
	
	private Long audioDuration;
	
	private Long readDuration;
	
	private int authorId;
	
	private List<Integer> categId;

	
	private List<Integer> castCrewsId;

	private List<Integer> genresId;
	
	private List<Integer> tagsId;

	public BookRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookRequestDto(String title, Date releaseDate, String description, Long audioDuration, Long readDuration,
			 List<Integer> castCrewsId, List<Integer> genresId,List<Integer> categId,
			List<Integer> tagsId,int authorId) {
		super();
		this.title = title;
		this.releaseDate = releaseDate;
		this.description = description;
		this.audioDuration = audioDuration;
		this.readDuration = readDuration;
		this.castCrewsId = castCrewsId;
		this.genresId = genresId;
		this.tagsId = tagsId;
		this.categId=categId;
		this.authorId=authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void setAudioDuration(Long audioDuration) {
		this.audioDuration = audioDuration;
	}

	public Long getReadDuration() {
		return readDuration;
	}

	public void setReadDuration(Long readDuration) {
		this.readDuration = readDuration;
	}


	public List<Integer> getCastCrewsId() {
		return castCrewsId;
	}

	public void setCastCrewsId(List<Integer> castCrewsId) {
		this.castCrewsId = castCrewsId;
	}

	public List<Integer> getGenresId() {
		return genresId;
	}

	public void setGenresId(List<Integer> genresId) {
		this.genresId = genresId;
	}

	public List<Integer> getTagsId() {
		return tagsId;
	}

	public void setTagsId(List<Integer> tagsId) {
		this.tagsId = tagsId;
	}

	public List<Integer> getCategId() {
		return categId;
	}

	public void setCategId(List<Integer> categId) {
		this.categId = categId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	


}
