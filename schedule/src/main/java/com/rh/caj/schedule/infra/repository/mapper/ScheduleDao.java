package com.rh.caj.schedule.infra.repository.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.caj.schedule.infra.repository.po.SchedulePo;

public interface ScheduleDao extends JpaRepository<SchedulePo, String> {

}
