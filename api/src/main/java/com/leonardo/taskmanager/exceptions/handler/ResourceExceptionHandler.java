package com.leonardo.taskmanager.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import com.leonardo.taskmanager.exceptions.DataPersistenceException;
import com.leonardo.taskmanager.exceptions.ObjectNotFoundException;
import com.leonardo.taskmanager.exceptions.dto.StandardError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> notFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				"Esse objeto não pode ser encontrado.",
				e.getMessage(),
				request.getRequestURI()
		);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}


	@ExceptionHandler(DataPersistenceException.class)
	public ResponseEntity<StandardError> dataPersistence(DataPersistenceException e, HttpServletRequest request){
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Esse objeto não pode ser salvo.",
				e.getMessage(),
				request.getRequestURI()
		);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
