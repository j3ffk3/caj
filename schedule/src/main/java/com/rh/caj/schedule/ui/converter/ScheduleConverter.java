package com.rh.caj.schedule.ui.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.caj.schedule.domain.schedlue.DeparturesVo;
import com.rh.caj.schedule.domain.schedlue.ScheduleDo;
import com.rh.caj.schedule.ui.dto.ScheduleRequestDTO;

@Service
public class ScheduleConverter {

	/**
	 * toDo
	 * @param scheduleRequestDTO
	 * @return
	 */
	public ScheduleDo toDo(ScheduleRequestDTO scheduleRequestDTO) {
		ScheduleDo schduleDo =new ScheduleDo();
		schduleDo.setTrainNo(scheduleRequestDTO.getTrainNo());
		schduleDo.setPattern(scheduleRequestDTO.getStoppingPattern());
		schduleDo.setFreq(scheduleRequestDTO.getFreq());
		schduleDo.setDirection(scheduleRequestDTO.getDirection());
		List<DeparturesVo> departureList=new ArrayList<>();
		scheduleRequestDTO.getDepartures().stream().forEach(item->{
			DeparturesVo departuresVo=new DeparturesVo();
			departuresVo.setStation(item.getStation());
			departuresVo.setDepartureTime(item.getDepartureTime());
			departuresVo.setArrivalTime(item.getArrivalTime());
			departureList.add(departuresVo);
		});
		schduleDo.setDepartures(departureList);
		return schduleDo;
	}
}
