package com.kodluyoruz.exceptions;

import com.kodluyoruz.enums.BaseExceptionType;
import lombok.Builder;

import java.util.List;

public class RestMethodArgumentNotValidException extends RestException {

	public RestMethodArgumentNotValidException() {
		super(BaseExceptionType.REST_METHOD_ARGUMENT_NOT_VALID_EXCEPTION, null, null);
	}

	@Builder
	private RestMethodArgumentNotValidException(String message, List<String> errors) {
		super(BaseExceptionType.REST_METHOD_ARGUMENT_NOT_VALID_EXCEPTION, message, errors);
	}

}