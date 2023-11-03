package com.bikkadit.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bikkadit.helper.APIresponse;

//@RestControllerAdvice annotaion used to handle exception globly
//exeption may create any where but it will handle by this class

@RestControllerAdvice
public class GlobleExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIresponse> ResourceNotFoundException(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		APIresponse apIresponse = new APIresponse(message, "false");

		return new ResponseEntity<APIresponse>(apIresponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		Map<String, String> response = new HashMap<String, String>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();

			response.put(field, message);
		});

		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

}
