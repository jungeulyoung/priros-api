package com.lnt.priros.controller;

import java.util.Objects;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.lnt.priros.exception.GlobalException;
import com.lnt.priros.model.response.ErrorResponse;
import com.lnt.priros.resource.ErrorCode;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RestController
public class GlobalExceptionController {

	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(GlobalException error) {
		final ErrorResponse errorResponse = new ErrorResponse(error.getErrorCode());
		return new ResponseEntity<>(errorResponse, Objects.requireNonNull(HttpStatus.resolve(errorResponse.getStatus())));
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	// 모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception e) {
		final ErrorResponse response = new ErrorResponse(ErrorCode.SERVICE_ERROR);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}    
    
}
