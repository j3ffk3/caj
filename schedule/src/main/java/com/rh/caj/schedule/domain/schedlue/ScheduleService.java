package com.rh.caj.schedule.domain.schedlue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class ScheduleService {

	/**
	 * scheduleRepository
	 */
	@Autowired
	ScheduleRepository scheduleRepository;

	/**
	 * createSchedule
	 * @throws JsonProcessingException 
	 */
	public void createSchedule(ScheduleDo scheduleDo) throws JsonProcessingException {
		scheduleRepository.createSchedule(scheduleDo);
	}

	/**
	 * getSchedule
	 */
	public List<ScheduleDo> getSchedule() {
		return scheduleRepository.findSchedules();
	}
}
