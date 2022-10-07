package com.rh.caj.masterfile.domain.station;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {

	/**
	 * stationRepository
	 */
	@Autowired
	StationRepository stationRepository;
	
	/**
	 * getStationDoList
	 * @return
	 */
	public List<StationDo> getStationDoList(){
		return stationRepository.findStations();
	}
}
