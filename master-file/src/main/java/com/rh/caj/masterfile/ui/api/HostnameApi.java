package com.rh.caj.masterfile.ui.api;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rh.caj.masterfile.ui.dto.GenericResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
public class HostnameApi {

	@GetMapping(value = "/api/hostname", produces = "application/json")
	public ResponseEntity<GenericResponseDTO> getFare(@PathVariable("type") String type)
			throws JsonMappingException, JsonProcessingException {
		log.info("GET /api/hostname");
		return new ResponseEntity<>(GenericResponseDTO.builder().data(Optional.ofNullable(System.getenv("HOSTNAME")))
				.code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.name()).build(), HttpStatus.OK);
	}
}
