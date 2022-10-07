package com.rh.caj.masterfile.infra.repository.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STATION")
public class StationPo {

	@Id
	@Column(name = "STATION_ID")
	private String stationId;
	
	@Column(name = "STATION_NAME")
	private String stationName;
	
	@Column(name = "STATION_ENG_NAME")
	private String stationEngName;
	
	@Column(name = "STATION_CODE")
	private String stationCode;
	
	@Column(name = "STATION_ORDER")
	private String stationOrder;
	
	@Column(name = "STATION_DISTANCE")
	private int stationDistance;
}
