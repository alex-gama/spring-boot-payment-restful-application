package com.application.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorResponse {

	private String message;

	private String detail;

	private int status;

	private String date;
	
	private Map<String, List<ValidationError>> errors = new HashMap<>();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void addError(String field, List<ValidationError> validation) {
		errors.put(field, validation);
	}

}
