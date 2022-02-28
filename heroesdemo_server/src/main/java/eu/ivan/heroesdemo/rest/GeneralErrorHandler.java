package eu.ivan.heroesdemo.rest;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralErrorHandler {
	
	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<String> handleNotFoundExceptions(Exception e) {
		
		return ResponseEntity.notFound().build();
		
	}

}
