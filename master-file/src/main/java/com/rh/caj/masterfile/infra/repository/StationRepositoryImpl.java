package com.rh.caj.masterfile.infra.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh.caj.masterfile.domain.station.StationDo;
import com.rh.caj.masterfile.domain.station.StationRepository;
import com.rh.caj.masterfile.infra.repository.mapper.StationDao;

@Service
public class StationRepositoryImpl implements StationRepository {

	@Autowired
	StationDao stationDao;

	@Override
	public List<StationDo> findStations() {
		List<StationDo> stationDoList=new ArrayList<>();
		stationDao.findAll().forEach(station -> {
			StationDo stationDo=new StationDo();
			stationDo.setStationCode(station.getStationCode());
			stationDo.setStationDistance(station.getStationDistance());
			stationDo.setStationEngName(station.getStationEngName());
			stationDo.setStationId(station.getStationId());
			stationDo.setStationName(station.getStationName());
			stationDo.setStationOrder(station.getStationOrder());
			stationDoList.add(stationDo);
		});
		return stationDoList;
	}

}
