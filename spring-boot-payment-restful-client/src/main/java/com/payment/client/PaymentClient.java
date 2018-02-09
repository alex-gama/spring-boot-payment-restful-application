package com.payment.client;

import org.springframework.web.client.RestTemplate;

public class PaymentClient {

	private static final String PAYMENT_URI = "http://localhost:8080/payments";

	private static final RestTemplate restTemplate = new RestTemplate();

	public PaymentDTO findBy(Long id) {
		PaymentDTO paymentDTO = restTemplate.getForObject(PAYMENT_URI + "/" + id, PaymentDTO.class);

		return paymentDTO;
	}
	
}
