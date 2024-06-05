package com.bookRealm.api_v1.entity;

import java.time.Duration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="audioepisodes")
public class AudioEpisodes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="audio_id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="duration")
	private Long audioDuration;
	
	
	@Column(name="audio_url")
	private String audio_url;
	
	@Column(name="image_url")
	private String image_url;

	public AudioEpisodes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AudioEpisodes(String title, Long audioDuration, String audio_url, String image_url) {
		super();
		this.title = title;
		this.audioDuration = audioDuration;
		this.audio_url = audio_url;
		this.image_url = image_url;
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

	public Duration getAudioDuration() {
		return audioDuration!=null?Duration.ofSeconds(audioDuration):null;

	}

	public void setAudioDuration(Duration audioDuration) {
		this.audioDuration = audioDuration!=null?audioDuration.getSeconds():null;
	}

	public String getAudio_url() {
		return audio_url;
	}

	public void setAudio_url(String audio_url) {
		this.audio_url = audio_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
	

}
