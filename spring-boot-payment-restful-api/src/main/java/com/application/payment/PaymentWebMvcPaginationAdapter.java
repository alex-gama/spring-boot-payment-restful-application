package com.application.payment;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PaymentWebMvcPaginationAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver pageableHandler = new PageableHandlerMethodArgumentResolver();
		pageableHandler.setFallbackPageable(new PageRequest(0, 3));
		argumentResolvers.add(pageableHandler);
		super.addArgumentResolvers(argumentResolvers);
	}	
	
}
