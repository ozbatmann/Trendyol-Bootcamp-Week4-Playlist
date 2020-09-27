package com.kodluyoruz.exceptions;

import com.kodluyoruz.enums.BaseExceptionType;

import java.util.List;

public class RestException extends BaseException {

	public RestException(BaseExceptionType type, String message, List<String> errors) {
		super(type, message, errors);
	}

}
