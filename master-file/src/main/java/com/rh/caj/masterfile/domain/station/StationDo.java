package com.rh.caj.masterfile.domain.station;

import lombok.Data;

@Data
public class StationDo {

	/**
	 * stationId
	 */
	private String stationId;	

	/**
	 * stationName
	 */
	private String stationName;
	
	/**
	 * stationEngName
	 */
	private String stationEngName;
	
	/**
	 * stationCode
	 */
	private String stationCode;
	
	/**
	 * stationOrder
	 */
	private String stationOrder;
	
	/**
	 * stationDistance
	 */
	private int stationDistance;
}
