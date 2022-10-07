package com.rh.caj.schedule.ui.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ScheduleRequestDTO {

	String trainNo;
	
	String direction;
	
	String stoppingPattern;
	
	String freq;
	
	List<DeparturesDTO> departures;
}
