package com.application.payment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/payments")
public class PaymentV2Controller {
	
	private PaymentRepository paymentRepository;
	
	private PaymentDTOConverter converter;
	
	@Autowired
	public PaymentV2Controller(PaymentRepository paymentRepository, PaymentDTOConverter converter) {
		this.paymentRepository = paymentRepository;
		this.converter = converter;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentV2DTO> findById(@PathVariable("id") Long id) {
	    Optional<Payment> payment = paymentRepository.findById(id);
	    if (!payment.isPresent()) {
	    	throw new ResourceNotFoundException(String.format("Payment was not found {%d}", id));
	    }
	    PaymentV2DTO paymentDTO = converter.fromV2(payment.get());
	    return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
	}
	
}
