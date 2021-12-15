package com.performance.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performance.service.entity.Evaluated;



public interface IEvaluatedRepository extends JpaRepository<Evaluated, Integer>{
	
	List<Evaluated> findByEmailEvaluated(String email);

}
