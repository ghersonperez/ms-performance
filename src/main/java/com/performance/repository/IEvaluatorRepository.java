package com.performance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.performance.dto.TeamInterface;
import com.performance.dto.TrackingInterface;
import com.performance.entity.Evaluator;

public interface IEvaluatorRepository extends JpaRepository<Evaluator, Integer> {
	
	@Query(nativeQuery = true, value = "call sp_team_performance(?1)")
	List<TeamInterface> searchTeam(String idssff);
	@Query(nativeQuery = true, value = "call sp_tracking(?1,?2)")
	List<TrackingInterface> tracking(int page,int vsize);
	
	@Query(nativeQuery = true, value = "call sp_count_tracking()")
	Integer countTracking();
	

}
