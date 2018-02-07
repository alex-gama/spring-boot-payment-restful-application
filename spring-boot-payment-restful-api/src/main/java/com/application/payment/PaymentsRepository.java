package com.application.payment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.application.buyer.Buyer;

@Component
public class PaymentsRepository {

	private Map<Long, Payment> paymentsDatabase = new HashMap<>();

	private AtomicLong generatorId = new AtomicLong(3);

	public PaymentsRepository() {
		paymentsDatabase.put(1L, new Payment(1L, new BigDecimal("100"), "Buying something cool", new Buyer(1L, "Alex Gama")));
		paymentsDatabase.put(2L, new Payment(2L, new BigDecimal("200"), "Buying something really great", new Buyer(2L, "Will Smith")));
		paymentsDatabase.put(3L, new Payment(3L, new BigDecimal("300"), "Buying something not so good", new Buyer(3L, "Jessica Thompson")));
	}

	public List<Payment> getPayments() {
		return paymentsDatabase.values().stream().collect(Collectors.toList()); //Java 8 rocks :)
	}

	public Payment save(Payment payment) {
	    Long id = generatorId.incrementAndGet();
	    payment.setId(id);
	    paymentsDatabase.put(id, payment);
	    return payment;
	}

	public Optional<Payment> findBy(Long id) {
	    return Optional.ofNullable(paymentsDatabase.get(id));
	}

	public void update(Payment payment) {
	    paymentsDatabase.put(payment.getId(), payment);
	}

	public void updateDescription(Payment payment) {
	    Payment paymentToUpdate = paymentsDatabase.get(payment.getId());
	    paymentToUpdate.setDescription(payment.getDescription());
	    paymentsDatabase.put(payment.getId(), paymentToUpdate);
	}

	public void delete(Long id) {
	    paymentsDatabase.remove(id);
	}

}