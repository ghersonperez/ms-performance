package com.performance.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.performance.service.dto.TeamInterface;
import com.performance.service.dto.TrackingInterface;
import com.performance.service.entity.Evaluator;



public interface IEvaluatorRepository extends JpaRepository<Evaluator, Integer> {
	
	@Query(nativeQuery = true, value = "call sp_team_performance(?1)")
	List<TeamInterface> searchTeam(String idssff);
	@Query(nativeQuery = true, value = "call sp_tracking(?1,?2,?3)")
	List<TrackingInterface> tracking(int page,int vsize,String filter);
	
	@Query(nativeQuery = true, value = "call sp_count_tracking(?1)")
	Integer countTracking(String filter);
	
	List<Evaluator> findByIdEvaluated(Integer idevaluated);
	
	@Query(nativeQuery=true, value="call sp_report_evaluacion(?1)")
	public List<Object[]> reportEvaluacion(Integer process);
	

}
