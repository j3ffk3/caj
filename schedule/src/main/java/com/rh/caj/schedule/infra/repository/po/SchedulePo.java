package com.rh.caj.schedule.infra.repository.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "SCHEDULE")
public class SchedulePo {
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	long id;
	
	/**
	 * trainNo
	 */
	@Column(name = "TRAIN_NO")
	String trainNo;
	
	/**
	 * direction
	 */
	@Column(name = "DIRECTION")
	String direction;
	
	/**
	 * freq
	 */
	@Column(name = "FREQ")
	String freq;
	
	/**
	 * pattern
	 */
	@Column(name = "PATTERN")
	String pattern;
	
	/**
	 * departures
	 */
	@Lob
	@Column(name = "DEPARTURES")
	String departures;

}
