package com.rh.caj.masterfile.domain.stopping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoppingService {
	
	@Autowired
	StoppingRepository stoppingRepository;
	
	/**
	 * getPatterns
	 * @return
	 */
	public StoppingDo getPattern(String patternId) {
		return stoppingRepository.findStoppingPattern(patternId);
	}
	
	/**
	 * getPatterns
	 * @return
	 */
	public List<StoppingDo> getPatterns() {
		return stoppingRepository.findStoppingPatterns();
	}
	
}
