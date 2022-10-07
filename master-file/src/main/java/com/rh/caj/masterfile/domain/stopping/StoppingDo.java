package com.rh.caj.masterfile.domain.stopping;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class StoppingDo {

	/**
	 * pattern
	 */
	String pattern;
	
	/**
	 * patternName
	 */
	String patternName;

	/**
	 * stops
	 */
	List<StationVo> stops = new ArrayList<>();

}
