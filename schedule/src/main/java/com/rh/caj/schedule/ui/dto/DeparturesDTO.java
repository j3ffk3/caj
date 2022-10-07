package com.rh.caj.schedule.ui.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class DeparturesDTO {
	
	/**
	 * station
	 */
	String station;
	
	/**
	 * arrivalTime
	 */
	String arrivalTime;
	
	/**
	 * departureTime
	 */
	String departureTime;
}
