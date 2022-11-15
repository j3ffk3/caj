package com.rh.caj.schedule.infra.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rh.caj.schedule.domain.schedlue.DeparturesVo;
import com.rh.caj.schedule.domain.schedlue.ScheduleDo;
import com.rh.caj.schedule.domain.schedlue.ScheduleRepository;
import com.rh.caj.schedule.infra.repository.mapper.ScheduleDao;
import com.rh.caj.schedule.infra.repository.po.SchedulePo;

@Service
public class ScheduleRepositoryImpl implements ScheduleRepository {

	/**
	 * scheduleDao
	 */
	@Autowired
	ScheduleDao scheduleDao;

	@Override
	public void createSchedule(ScheduleDo scheduleDo) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		SchedulePo schedulePo = new SchedulePo();
		schedulePo.setTrainNo(scheduleDo.getTrainNo());
		schedulePo.setPattern(scheduleDo.getPattern());
		schedulePo.setFreq(scheduleDo.getFreq());
		schedulePo.setDirection(scheduleDo.getDirection());
		schedulePo.setDepartures(mapper.writeValueAsString(scheduleDo.getDepartures()));
		scheduleDao.save(schedulePo);

	}

	@Override
	public List<ScheduleDo> findSchedules() {
		ObjectMapper mapper=new ObjectMapper();
		List<ScheduleDo> scheduleDoList=new ArrayList<>();
		List<SchedulePo> schedulePoList= scheduleDao.findAll();
		schedulePoList.forEach(item->{
			ScheduleDo scheduleDo=new ScheduleDo();
			scheduleDo.setFreq(item.getFreq());
			scheduleDo.setDirection(item.getDirection());
			scheduleDo.setPattern(item.getPattern());
			scheduleDo.setTrainNo(item.getTrainNo());
			List<DeparturesVo> departuresVoList;
			try {
				departuresVoList = mapper.readValue(item.getDepartures(), new TypeReference<List<DeparturesVo>>(){});
				scheduleDo.setDepartures(departuresVoList);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scheduleDoList.add(scheduleDo);
		});
		return scheduleDoList;
	}

}
