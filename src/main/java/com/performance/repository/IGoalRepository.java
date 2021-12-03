package com.performance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performance.entity.Goal;

public interface IGoalRepository extends JpaRepository<Goal, Integer> {
	
	List<Goal> findByIdEvaluated(Integer id);

}
