package com.application.payment;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

	Optional<Payment> findById(Long id);

}
