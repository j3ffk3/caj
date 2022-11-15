package com.rh.caj.masterfile.ui.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rh.caj.masterfile.application.StoppingPatternApplicationService;
import com.rh.caj.masterfile.ui.dto.GenericResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StoppingApi {
	
	/**
	 * stoppingPatternApplicationService
	 */
	@Autowired
	StoppingPatternApplicationService stoppingPatternApplicationService;

	@Autowired
	Tracer tracer;
	
	/**
	 * getStoppings
	 * @return
	 */
	@GetMapping(value = "/api/master-files/stoppings", produces = "application/json")
	public ResponseEntity<GenericResponseDTO> getStoppings() {
		log.info("GET /api/master-files/stoppings");
		return new ResponseEntity<>(
				GenericResponseDTO.builder()
				    .data(stoppingPatternApplicationService.getPatternList())
					.code(String.valueOf(HttpStatus.OK.value()))
					.message(HttpStatus.OK.name())
					.traceId(tracer.currentSpan().context().traceId())
					.build(), HttpStatus.OK);
	}
	
	/**
	 * getStopping
	 * @param stoppingId
	 * @return
	 */
	@GetMapping(value = "/api/master-files/stoppings/{stoppingId}", produces = "application/json")
	public ResponseEntity<GenericResponseDTO> getStopping(@PathVariable("stoppingId") String stoppingId) {
		log.info("GET /api/master-files/stoppings/{stoppingId},id:{}",stoppingId);
		return new ResponseEntity<>(
				GenericResponseDTO.builder()
				    .data(stoppingPatternApplicationService.getPattern(stoppingId))
					.code(String.valueOf(HttpStatus.OK.value()))
					.message(HttpStatus.OK.name())
					.build(), HttpStatus.OK);
	}
}
