package com.it.controller;

import java.time.LocalDateTime;

public class AppResponse {

	String code;
	String message;
	private LocalDateTime created=LocalDateTime.now();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "AppResponse [code=" + code + ", message=" + message + ", created=" + created + "]";
	}
	
	
}
