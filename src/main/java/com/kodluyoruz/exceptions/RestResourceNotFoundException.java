package com.kodluyoruz.exceptions;

import com.kodluyoruz.enums.BaseExceptionType;
import lombok.Builder;

import java.util.List;

public class RestResourceNotFoundException extends RestException {

	public RestResourceNotFoundException() {
		super(BaseExceptionType.REST_RESOURCE_NOT_FOUND_EXCEPTION, null, null);
	}

	@Builder
	private RestResourceNotFoundException(String message, List<String> errors) {
		super(BaseExceptionType.REST_RESOURCE_NOT_FOUND_EXCEPTION, message, errors);
	}
}
