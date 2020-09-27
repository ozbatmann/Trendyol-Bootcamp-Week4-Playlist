package com.kodluyoruz.exceptions;

import com.kodluyoruz.enums.BaseExceptionType;
import lombok.Builder;

import java.util.List;

public class RestGeneralException extends RestException {

	public RestGeneralException() {
		super(BaseExceptionType.REST_GENERAL_EXCEPTION, null, null);
	}

	@Builder
	private RestGeneralException(String message, List<String> errors) {
		super(BaseExceptionType.REST_GENERAL_EXCEPTION, message, errors);
	}
}
