package com.rh.caj.masterfile.infra.repository.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rh.caj.masterfile.infra.repository.po.StoppingPatternPo;

@Repository
public interface StoppingPatternDao extends JpaRepository<StoppingPatternPo, String> {

}
