package com.kodluyoruz.exceptions.controller;

import com.kodluyoruz.exceptions.RestGeneralException;
import com.kodluyoruz.exceptions.RestMethodArgumentNotValidException;
import com.kodluyoruz.exceptions.RestResourceNotFoundException;
import com.kodluyoruz.exceptions.model.RestApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler (Exception.class)
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		ex.printStackTrace();
		RestApiError apiError = RestApiError.builder()
				.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.exception(RestGeneralException.builder().message(ex.getLocalizedMessage()).errors(Arrays.asList(ex.getMessage())).build())
				.build();

		return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
	}

	@ExceptionHandler ({RestResourceNotFoundException.class})
	public ResponseEntity<Object> handleResourceNotFoundException(RestResourceNotFoundException ex, WebRequest request) {

		RestApiError apiError = RestApiError.builder()
				.httpStatus(HttpStatus.NOT_FOUND.value())
				.exception(RestResourceNotFoundException.builder().message(ex.getLocalizedMessage()).errors(Arrays.asList("Requested resource not found")).build())
				.build();
		return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for(FieldError error : ex.getBindingResult().getFieldErrors()){
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for(ObjectError error : ex.getBindingResult().getGlobalErrors()){
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		RestApiError apiError = RestApiError.builder()
				.httpStatus(HttpStatus.BAD_REQUEST.value())
				.exception(RestMethodArgumentNotValidException.builder().message(ex.getLocalizedMessage()).errors(errors).build())
				.build();

		return ResponseEntity.badRequest().headers(headers).body(apiError);
	}

}
