package com.rh.caj.masterfile.ui.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rh.caj.masterfile.application.StationApplicationService;
import com.rh.caj.masterfile.ui.dto.GenericResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StationApi {
	
	/**
	 * stationApplicationService
	 */
	@Autowired
	StationApplicationService stationApplicationService;

	@Autowired
	Tracer tracer;
	
	/**
	 * getStations
	 * @return
	 */
	@GetMapping(value = "/api/master-files/stations", produces = "application/json")
	public ResponseEntity<GenericResponseDTO> getStations() {
		log.info("GET /api/master-files/stations");
		return new ResponseEntity<>(
				GenericResponseDTO.builder()
				    .data(stationApplicationService.getStationList())
					.code(String.valueOf(HttpStatus.OK.value()))
					.message(HttpStatus.OK.name())
					.traceId(tracer.currentSpan().context().traceId())
					.build(), HttpStatus.OK);
	}
	
}
