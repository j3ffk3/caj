package com.rh.caj.masterfile.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh.caj.masterfile.domain.stopping.StoppingDo;
import com.rh.caj.masterfile.domain.stopping.StoppingService;

@Service
public class StoppingPatternApplicationService {

	/**
	 * stoppingService
	 */
	@Autowired
	StoppingService stoppingService;
	
	/*
	 * getPattern
	 */
	public StoppingDo getPattern(String patternId){
		return stoppingService.getPattern(patternId);
	}
	
	/**
	 * getPatternList
	 * @return
	 */
	public List<StoppingDo> getPatternList(){
		return stoppingService.getPatterns();
	}
	

}
