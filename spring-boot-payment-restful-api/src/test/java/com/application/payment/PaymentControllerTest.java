package com.application.payment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.application.buyer.BuyerRepository;

@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {

	@Mock
	private PaymentRepository paymentRepository;

	@Mock
	private BuyerRepository buyerRepository;

	@Mock
	private PaymentDTOConverter converter;

	@Test(expected = ResourceNotFoundException.class)
	public void shouldThrowsAnExceptionWhenPaymentDoesNotExist() throws Exception {
		PaymentController controller = new PaymentController(paymentRepository, buyerRepository, converter);

		when(paymentRepository.findById(10L)).thenReturn(Optional.empty());

		controller.findById(10L);
	}

	@Test
	public void shouldRetrieveAPayment() throws Exception {
		PaymentController controller = new PaymentController(paymentRepository, buyerRepository, converter);

		Payment payment = new Payment();
		when(paymentRepository.findById(10L)).thenReturn(Optional.of(payment));

		PaymentDTO paymentDTO = new PaymentDTO();
		when(converter.from(payment)).thenReturn(paymentDTO);

		ResponseEntity<PaymentDTO> response = controller.findById(10L);

		assertEquals(paymentDTO, response.getBody());
	}
	
}
