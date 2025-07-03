package com.analekh.electronic.store.ElectronicStroreeeee.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.analekh.electronic.store.ElectronicStroreeeee.dto.ApiResponseMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	//handler resource not found exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		logger.info("Exception Handler invoke !!");
		
		ApiResponseMessage response = ApiResponseMessage.builder()
				.message(ex.getMessage())
				.status(HttpStatus.OK)
				.success(true)
				.build();
		
		return new ResponseEntity<ApiResponseMessage>(response,HttpStatus.NOT_FOUND);
	}
	
	//MethhodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		
		Map<String, Object> response = new HashMap<>();
		allErrors.stream().forEach(objectError -> {
			String message = objectError.getDefaultMessage();
			String field = ((FieldError) objectError).getField();
			response.put(field, message);
		});
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
	}
	
}
