package com.rh.caj.masterfile.ui.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rh.caj.masterfile.application.StationApplicationService;
import com.rh.caj.masterfile.ui.dto.GenericResponseDTO;

@RestController
public class StationApi {
	
	/**
	 * stationApplicationService
	 */
	@Autowired
	StationApplicationService stationApplicationService;

	/**
	 * getStations
	 * @return
	 */
	@GetMapping(value = "/api/master-files/stations", produces = "application/json")
	public ResponseEntity<GenericResponseDTO> getStations() {
		// TODO
		return new ResponseEntity<>(
				GenericResponseDTO.builder()
				    .data(stationApplicationService.getStationList())
					.code(String.valueOf(HttpStatus.OK.value()))
					.message(HttpStatus.OK.name())
					.build(), HttpStatus.OK);
	}
	
}
