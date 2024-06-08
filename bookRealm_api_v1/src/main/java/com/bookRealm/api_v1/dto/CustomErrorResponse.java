package com.bookRealm.api_v1.dto;

public class CustomErrorResponse {
	
	private boolean success;
	
	private int status;
	
	private String message;
	
	private Long currentTime;

	public CustomErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomErrorResponse(boolean success, int status, String message, Long currentTime) {
		super();
		this.success = success;
		this.status = status;
		this.message = message;
		this.currentTime = currentTime;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}
	
	

}
