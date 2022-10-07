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
@Table(name = "STOPPING_PATTERN")
public class StoppingPatternPo {

	@Id
	@Column(name = "STOPPING_PATTERN_ID")
	private String StoppingPatternId;
	
	@Column(name = "STOPPING_PATTERN_NAME")
	private String StoppingPatternName;
	
	@Column(name = "STOPPING_PATTERN_DETAIL")
	private String StoppingPatternDetail;
}
