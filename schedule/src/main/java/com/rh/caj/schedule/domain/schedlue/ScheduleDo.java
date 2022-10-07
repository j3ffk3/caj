package com.rh.caj.schedule.domain.schedlue;

import java.util.List;

import lombok.Data;

@Data
public class ScheduleDo {

	/**
	 * trainNo
	 */
	String trainNo;
	
	/**
	 * direction
	 */
	String direction;
	
	/**
	 * freq
	 */
	String[] freq;
	
	/**
	 * duration
	 */
	String duration;
	
	/**
	 * pattern
	 */
	String pattern;
	
	/**
	 * departures
	 */
	List<DeparturesVo> departures;
}
