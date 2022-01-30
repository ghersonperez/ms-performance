package com.performance.service.repository;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performance.service.entity.GoalComment;



public interface IGoalCommentRepository extends JpaRepository<GoalComment,Integer > {
	
	Optional<GoalComment> findByIdGoalAndIdEvaluator(Integer idgoal,Integer tor);
	@Transactional
	void deleteByIdEvaluator(Integer id);
}
