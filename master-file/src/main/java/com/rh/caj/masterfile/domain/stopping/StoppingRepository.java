package com.rh.caj.masterfile.domain.stopping;

import java.util.List;

public interface StoppingRepository {
	
	/**
	 * findStoppingPattern
	 * @return
	 */
	StoppingDo findStoppingPattern(String patternId);
	
	/**
	 * findStoppingPatterns
	 * @return
	 */
	List<StoppingDo> findStoppingPatterns();
	
}
