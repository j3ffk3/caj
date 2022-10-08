package com.rh.caj.masterfile.infra.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh.caj.masterfile.domain.stopping.StationVo;
import com.rh.caj.masterfile.domain.stopping.StoppingDo;
import com.rh.caj.masterfile.domain.stopping.StoppingRepository;
import com.rh.caj.masterfile.infra.repository.mapper.StationDao;
import com.rh.caj.masterfile.infra.repository.mapper.StoppingPatternDao;
import com.rh.caj.masterfile.infra.repository.po.StationPo;
import com.rh.caj.masterfile.infra.repository.po.StoppingPatternPo;

@Service
public class StoppingRepositoryImpl implements StoppingRepository {

	@Autowired
	StationDao stationDao;

	@Autowired
	StoppingPatternDao stoppingPatternDao;

	@Override
	public StoppingDo findStoppingPattern(String patternId) {
		
		StoppingPatternPo stoppingPatternPo= stoppingPatternDao.findById(patternId).get();
		StoppingDo stoppingDo = new StoppingDo();
		stoppingDo.setPattern(stoppingPatternPo.getStoppingPatternId());
		stoppingDo.setPatternName(stoppingPatternPo.getStoppingPatternName());
		List<String> stationList = Arrays.asList(stoppingPatternPo.getStoppingPatternDetail().split(","));
		stationList.forEach(station -> {
			StationPo stationPo = stationDao.findById(station).get();
			StationVo stationVo = new StationVo();
			stationVo.setStationId(stationPo.getStationId());
			stationVo.setStationName(stationPo.getStationName());
			stoppingDo.getStops().add(stationVo);
		});
		return stoppingDo;
	}

	@Override
	public List<StoppingDo> findStoppingPatterns() {
		List<StoppingDo> stoppingDoList = new ArrayList<>();

		stoppingPatternDao.findAll().forEach(item -> {
			StoppingDo stoppingDo = new StoppingDo();
			stoppingDo.setPattern(item.getStoppingPatternId());
			stoppingDo.setPatternName(item.getStoppingPatternName());
			List<String> stationList = Arrays.asList(item.getStoppingPatternDetail().split(","));
			stationList.forEach(station -> {
				StationPo stationPo = stationDao.findById(station).get();
				StationVo stationVo = new StationVo();
				stationVo.setStationId(stationPo.getStationId());
				stationVo.setStationName(stationPo.getStationName());
				stoppingDo.getStops().add(stationVo);
			});
			stoppingDoList.add(stoppingDo);
		});
		return stoppingDoList;
	}

}
