package com.performance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performance.entity.Evaluated;

public interface IEvaluatedRepository extends JpaRepository<Evaluated, Integer>{
	
	List<Evaluated> findByIdssff(String idssff);

}
