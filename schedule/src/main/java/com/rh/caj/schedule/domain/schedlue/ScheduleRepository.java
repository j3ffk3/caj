package com.rh.caj.schedule.domain.schedlue;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScheduleRepository {

	/**
	 * findSchedules
	 * @return
	 */
	List<ScheduleDo> findSchedules();

	/*
	 * createSchedule
	 */
	void createSchedule(ScheduleDo scheduleDo) throws JsonProcessingException;
}
