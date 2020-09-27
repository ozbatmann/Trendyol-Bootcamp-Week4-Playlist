package com.kodluyoruz.exceptions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kodluyoruz.exceptions.BaseException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestApiError {

	private int httpStatus;
	private BaseException exception;

	@Builder.Default
	@JsonFormat (pattern = "YYYY-MM-dd HH:mm")
	private LocalDateTime timestamp = LocalDateTime.now();

}
