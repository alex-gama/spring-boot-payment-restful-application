package com.application.buyer;

public class Buyer {

	private Long id;

	private String name;

	//Required by Jackson to deserialize the JSON object
	public Buyer() {}

	public Buyer(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
