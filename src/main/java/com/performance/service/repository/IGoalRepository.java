package com.performance.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performance.service.entity.Goal;



public interface IGoalRepository extends JpaRepository<Goal, Integer> {
	
	List<Goal> findByIdEvaluated(Integer id);

}
