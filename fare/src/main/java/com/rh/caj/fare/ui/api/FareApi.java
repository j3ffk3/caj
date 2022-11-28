package com.rh.caj.fare.ui.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rh.caj.fare.application.FareApplicationService;
import com.rh.caj.fare.ui.dto.GenericResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FareApi {

	
	@Autowired
	FareApplicationService fareApplicationService;
	
	@Autowired
	Tracer tracer;

	@GetMapping(value = "/api/fare/{type}", produces = "application/json")
	public ResponseEntity<GenericResponseDTO> getFare(@PathVariable("type") String type)
			throws JsonMappingException, JsonProcessingException {
		log.info("GET /api/fare/{type}");	
		return new ResponseEntity<>(GenericResponseDTO.builder()
				.data(fareApplicationService.getFareTable(type))
				.code(String.valueOf(HttpStatus.OK.value()))
				.message(HttpStatus.OK.name())
				.traceId(tracer.currentSpan().context().traceId())
				.build(), HttpStatus.OK);
	}
	
	@ExceptionHandler(ResourceAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<GenericResponseDTO> handleResourceAccessException(ResourceAccessException exception){
		log.info(exception.getMessage());
		return new ResponseEntity<>(GenericResponseDTO.builder()
				.data(exception.getMessage())
				.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.message(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.traceId(tracer.currentSpan().context().traceId())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<GenericResponseDTO> handleHttpServerErrorException(HttpServerErrorException exception){
		log.info(exception.getMessage());
		return new ResponseEntity<>(GenericResponseDTO.builder()
				.data(exception.getMessage())
				.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.message(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.traceId(tracer.currentSpan().context().traceId())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
