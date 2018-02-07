package com.application.payment;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private PaymentsRepository paymentsRepository;
	private PaymentDTOConverter converter;

	@Autowired
	public PaymentController(PaymentsRepository paymentsRepository, PaymentDTOConverter converter) {
		this.paymentsRepository = paymentsRepository;
		this.converter = converter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PaymentDTO>> getPaymentsRepository() {
	    List<Payment> payments = paymentsRepository.getPayments();

	    List<PaymentDTO> paymentsDTO = converter.from(payments);

	    return new ResponseEntity<>(paymentsDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Payment> savePayment(@RequestBody PaymentDTO paymentDTO) {
		Payment payment = converter.from(paymentDTO);

	    Payment paymentSaved = paymentsRepository.save(payment);

	    HttpHeaders headers = new HttpHeaders();
	    URI uri = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(paymentSaved.getId())
	            .toUri();
	    headers.setLocation(uri);

	    return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentDTO> findById(@PathVariable("id") Long id) {
	    Optional<Payment> payment = paymentsRepository.findBy(id);
	    if (!payment.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    PaymentDTO paymentDTO = converter.from(payment.get());
	    return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
		Optional<Payment> paymentOptional = paymentsRepository.findBy(payment.getId());
	    if (!paymentOptional.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    paymentsRepository.update(payment);
	    return new ResponseEntity<>(paymentOptional.get(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public ResponseEntity<Payment> updateDescription(@RequestBody Payment payment) {
		Optional<Payment> paymentToUpdate = paymentsRepository.findBy(payment.getId());
		if (!paymentToUpdate.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	    paymentsRepository.updateDescription(payment);
	    return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    if (!paymentsRepository.findBy(id).isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    paymentsRepository.delete(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
}
