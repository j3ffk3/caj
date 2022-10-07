package com.rh.caj.masterfile.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh.caj.masterfile.domain.station.StationDo;
import com.rh.caj.masterfile.domain.station.StationService;



@Service
public class StationApplicationService {
	
	@Autowired
	StationService stationService;
	
    public List<StationDo> getStationList(){
		return stationService.getStationDoList();
	}
}
