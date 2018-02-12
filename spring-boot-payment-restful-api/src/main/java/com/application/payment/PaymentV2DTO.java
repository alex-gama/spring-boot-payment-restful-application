package com.application.payment;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import com.application.buyer.Buyer;
import com.application.payment.Payment.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentV2DTO extends ResourceSupport {

	private Long id;

	@NotNull
	@JsonProperty("total_value")
	private BigDecimal total;

	@NotEmpty
	private String description;

	@JsonProperty("user")
	private Buyer buyer;

	private PaymentStatus status;

	public Long getPaymentId() {
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

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

}
