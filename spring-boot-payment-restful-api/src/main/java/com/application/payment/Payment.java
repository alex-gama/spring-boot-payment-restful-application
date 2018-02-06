package com.application.payment;

import java.math.BigDecimal;

import com.application.buyer.Buyer;

public class Payment {

	private Long id;

	private BigDecimal total;

	private String description;

	private Buyer buyer;

	//Required by Jackson to deserialize the JSON object
	public Payment() {}

	public Payment(Long id, BigDecimal total, String description, Buyer buyer) {
		this.id = id;
		this.total = total;
		this.description = description;
		this.buyer = buyer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

}
