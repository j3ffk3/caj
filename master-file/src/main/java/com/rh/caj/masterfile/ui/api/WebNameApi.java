package com.rh.caj.masterfile.ui.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rh.caj.masterfile.ui.dto.GenericResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WebNameApi {
	
	@Value("${caj-web.tittle}")
	String webTittle;
	
	@Autowired
	Tracer tracer;
	
	@GetMapping(value = "/api/master-files/web-name", produces = "application/json")
	public ResponseEntity<GenericResponseDTO> getFare()
			throws JsonMappingException, JsonProcessingException {
		log.info("GET /api/master-files/web-name");
		return new ResponseEntity<>(GenericResponseDTO.builder()
				.data(webTittle)
				.code(String.valueOf(HttpStatus.OK.value()))
				.message(HttpStatus.OK.name())
				.traceId(tracer.currentSpan().context().traceId())
				.build(), HttpStatus.OK);
	}
}
