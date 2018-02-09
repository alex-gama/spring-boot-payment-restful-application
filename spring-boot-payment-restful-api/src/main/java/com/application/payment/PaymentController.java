package com.application.payment;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.application.buyer.Buyer;
import com.application.buyer.BuyerController;
import com.application.buyer.BuyerRepository;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private PaymentRepository paymentRepository;
	private PaymentDTOConverter converter;
	private BuyerRepository buyerRepository;

	@Autowired
	public PaymentController(PaymentRepository paymentRepository, BuyerRepository buyerRepository, PaymentDTOConverter converter) {
		this.paymentRepository = paymentRepository;
		this.buyerRepository = buyerRepository;
		this.converter = converter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<PaymentDTO>> getPayments(Pageable pageable) {
		Page<Payment> paginatedPayments = paymentRepository.findAll(pageable);

		Page<PaymentDTO> payments = paginatedPayments
			.map(converter::from);

		return new ResponseEntity<>(payments, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Payment> savePayment(@Valid @RequestBody PaymentDTO paymentDTO) {
		Payment payment = converter.from(paymentDTO);

		Buyer buyer = payment.getBuyer();
		buyerRepository.save(buyer);

	    Payment paymentSaved = paymentRepository.save(payment);
	    PaymentDTO savedPaymentDTO = converter.from(paymentSaved);
	    
	    savedPaymentDTO
		    .add(ControllerLinkBuilder
		 		.linkTo(ControllerLinkBuilder
		 				.methodOn(BuyerController.class)
		 					.findBy(payment.getBuyer().getId())).withRel("buyer"));

	    HttpHeaders headers = new HttpHeaders();
	    URI uri = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(savedPaymentDTO.getId())
	            .toUri();
	    headers.setLocation(uri);

	    return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentDTO> findById(@PathVariable("id") Long id) {
	    Optional<Payment> payment = paymentRepository.findById(id);
	    if (!payment.isPresent()) {
	    	throw new ResourceNotFoundException(String.format("Payment was not found {%d}", id));
	    }
	    PaymentDTO paymentDTO = converter.from(payment.get());
	    return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
		Optional<Payment> paymentOptional = paymentRepository.findById(payment.getId());
	    if (!paymentOptional.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    paymentRepository.save(payment);
	    return new ResponseEntity<>(paymentOptional.get(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    if (!paymentRepository.findById(id).isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    paymentRepository.delete(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
}
