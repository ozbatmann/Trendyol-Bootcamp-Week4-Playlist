package com.kodluyoruz.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodluyoruz.enums.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode (callSuper = false)
@JsonIgnoreProperties ({"cause", "localizedMessage", "suppressed", "stackTrace"})
public class BaseException extends RuntimeException {

	private BaseExceptionType type;

	private String message;

	private List<String> errors;

}
