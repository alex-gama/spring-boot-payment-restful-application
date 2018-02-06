package com.application.payment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

	private PaymentsRepository paymentsRepository;

	@Autowired
	public PaymentController(PaymentsRepository paymentsRepository) {
		this.paymentsRepository = paymentsRepository;
	}

	@RequestMapping(path = "/payments", method = RequestMethod.GET)
	public List<Payment> getPaymentsRepository() {
		List<Payment> payments = paymentsRepository.getPayments();

		return payments;
	}

	@RequestMapping(path = "/payments", method = RequestMethod.POST)
	public void savePayment(@RequestBody Payment payment) {
	    paymentsRepository.save(payment);
	}

	@RequestMapping(path = "/payments/{id}", method = RequestMethod.GET)
	public ResponseEntity<Payment> findById(@PathVariable("id") Long id) {
	    Optional<Payment> payment = paymentsRepository.findBy(id);
	    if (!payment.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(payment.get(), HttpStatus.OK);
	}

	@RequestMapping(path = "/payments", method = RequestMethod.PUT)
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
		Optional<Payment> paymentOptional = paymentsRepository.findBy(payment.getId());
	    if (!paymentOptional.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    paymentsRepository.update(payment);
	    return new ResponseEntity<>(paymentOptional.get(), HttpStatus.OK);
	}

}
