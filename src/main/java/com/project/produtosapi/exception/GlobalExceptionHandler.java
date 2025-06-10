package com.project.produtosapi.exception;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)	
	public String productNotFound(ProductNotFoundException exception)
	{
		return (exception.getMessage());
	}

	@ExceptionHandler(BusinessRuleException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)	
	public String businessRuleViolation(BusinessRuleException exception)
	{
		return (exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST )	
	public String validationError(MethodArgumentNotValidException exception)
	{
		return (exception.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleConstraintViolation(ConstraintViolationException exception) {
		return "Validation error: " + exception.getMessage();
	}
}
