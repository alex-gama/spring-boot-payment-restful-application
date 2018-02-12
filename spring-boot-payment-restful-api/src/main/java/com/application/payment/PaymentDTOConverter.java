package com.application.payment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PaymentDTOConverter {

	public Payment from(PaymentDTO paymentDTO) {
		Payment payment = new Payment();
		payment.setId(paymentDTO.getPaymentId());
		payment.setDescription(paymentDTO.getDescription());
		payment.setBuyer(paymentDTO.getBuyer());
		payment.setTotal(paymentDTO.getTotal());
		return payment;
	}

    public PaymentDTO from(Payment payment) {
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setId(payment.getId());
		paymentDTO.setDescription(payment.getDescription());
		paymentDTO.setStatus(payment.getStatus());
		paymentDTO.setTotal(payment.getTotal());
		paymentDTO.setBuyer(payment.getBuyer());
		return paymentDTO;
	}
    
    public PaymentV2DTO fromV2(Payment payment) {
    	PaymentV2DTO paymentDTO = new PaymentV2DTO();
    	paymentDTO.setId(payment.getId());
    	paymentDTO.setDescription(payment.getDescription());
    	paymentDTO.setStatus(payment.getStatus());
    	paymentDTO.setTotal(payment.getTotal());
    	paymentDTO.setBuyer(payment.getBuyer());
    	return paymentDTO;
    }

    public List<PaymentDTO> from(List<Payment> payments) {
        List<PaymentDTO> paymentsDTO = payments
            .stream()
            .map(this::from)
            .collect(Collectors.toList());
        return paymentsDTO;
    }

}
