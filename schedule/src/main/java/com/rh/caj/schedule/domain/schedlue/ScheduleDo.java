package com.rh.caj.schedule.domain.schedlue;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	String freq;
	
	/**
	 * pattern
	 */
	String pattern;
	
	/**
	 * departures
	 */
	List<DeparturesVo> departures;
}
