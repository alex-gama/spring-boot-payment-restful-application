package com.application.payment;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.application.buyer.Buyer;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private BigDecimal total;

	private String description;

	@OneToOne
	private Buyer buyer;

	@Enumerated(value = EnumType.STRING)
	private PaymentStatus status;

	//Required by Jackson to deserialize the JSON object
	public Payment() {
		this.status = PaymentStatus.STARTED;
	}

	public Payment(Long id, BigDecimal total, String description, Buyer buyer, PaymentStatus status) {
		this.id = id;
		this.total = total;
		this.description = description;
		this.buyer = buyer;
	}

	public enum PaymentStatus {
		STARTED, WAITING, FINISHED;
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

	public PaymentStatus getStatus() {
		return status;
	}

}
