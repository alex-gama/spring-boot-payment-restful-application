package com.payment.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PaymentClientTest {

	@Test
	public void shouldRetrieveAPaymentByItsId() throws Exception {
		PaymentClient client = new PaymentClient();

		PaymentDTO payment = client.findBy(1L);

		assertEquals(1L, payment.getId(), 0);
		assertEquals("Buying a mac", payment.getDescription());
	}
	
}
