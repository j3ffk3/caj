package com.rh.caj.schedule.ui.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rh.caj.schedule.application.ScheduleApplicationService;
import com.rh.caj.schedule.ui.converter.ScheduleConverter;
import com.rh.caj.schedule.ui.dto.GenericResponseDTO;
import com.rh.caj.schedule.ui.dto.ScheduleRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ScheduleApi {
	
	@Autowired
	ScheduleConverter converter;
	
	@Autowired
	ScheduleApplicationService scheduleApplicationService;
	
	@Autowired
	Tracer tracer;
	
	/**
	 * createSchedule
	 * @param scheduleRequestDTO
	 * @return
	 * @throws JsonProcessingException 
	 */
	@PostMapping("/api/schedule")
	public ResponseEntity<GenericResponseDTO>  createSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO) throws JsonProcessingException {
		scheduleApplicationService.createSchedule(converter.toDo(scheduleRequestDTO));
		log.info("POST /api/schedule");
		return new ResponseEntity<>(
				GenericResponseDTO.builder()
					.code(String.valueOf(HttpStatus.OK.value()))
					.message(HttpStatus.OK.name())
					.traceId(tracer.currentSpan().context().traceId())
					.build(), HttpStatus.OK);
		
	}
	
	/**
	 * getSchedule
	 * @return
	 * @throws JsonProcessingException 
	 */
	@GetMapping(value = "/api/schedule", produces = "application/json")
	public ResponseEntity<GenericResponseDTO> getSchedule() throws JsonProcessingException {
		log.info("GET /api/schedule");
		return new ResponseEntity<>(
				GenericResponseDTO.builder()
				    .data(scheduleApplicationService.getSchedule())
					.code(String.valueOf(HttpStatus.OK.value()))
					.message(HttpStatus.OK.name())
					.traceId(tracer.currentSpan().context().traceId())
					.build(), HttpStatus.OK);
	}
	
}
