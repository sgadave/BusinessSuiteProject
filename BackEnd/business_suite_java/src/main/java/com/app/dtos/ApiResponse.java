package com.app.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private LocalDateTime timestamp;
	private String message;
	private String appStatus;
	public ApiResponse(String message) {
		super();
		this.message = message;
		this.timestamp=LocalDateTime.now();
	}
	
	public ApiResponse(String message,String appStatus) {
		super();
		this.message = message;
		this.timestamp=LocalDateTime.now();
		this.appStatus=appStatus;
	}
	
}
