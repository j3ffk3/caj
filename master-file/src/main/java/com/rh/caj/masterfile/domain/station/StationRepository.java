package com.rh.caj.masterfile.domain.station;

import java.util.List;

public interface StationRepository {

	/**
	 * findStations
	 * @return
	 */
	public List<StationDo> findStations();
}
