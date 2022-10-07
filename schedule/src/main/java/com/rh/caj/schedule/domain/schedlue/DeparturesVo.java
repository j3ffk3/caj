package com.rh.caj.schedule.domain.schedlue;

import lombok.Data;

@Data
public class DeparturesVo {

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
