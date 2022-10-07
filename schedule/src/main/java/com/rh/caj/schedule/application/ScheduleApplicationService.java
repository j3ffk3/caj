package com.rh.caj.schedule.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rh.caj.schedule.domain.schedlue.ScheduleDo;
import com.rh.caj.schedule.domain.schedlue.ScheduleService;

@Service
public class ScheduleApplicationService {
	
	@Autowired
	ScheduleService scheduleService;

	/**
	 * createSchedule
	 * @throws JsonProcessingException 
	 */
	public void createSchedule(ScheduleDo scheduleDo) throws JsonProcessingException {
		scheduleService.createSchedule(scheduleDo);
	}
	
	/**
	 * createSchedule
	 * @throws JsonProcessingException 
	 */
	public List<ScheduleDo> getSchedule() throws JsonProcessingException {
		return scheduleService.getSchedule();
	}
	
}
