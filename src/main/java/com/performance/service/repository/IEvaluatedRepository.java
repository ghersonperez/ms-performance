package com.performance.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.performance.service.entity.Evaluated;



public interface IEvaluatedRepository extends JpaRepository<Evaluated, Integer>{
	
	List<Evaluated> findByEmailEvaluated(String email);
	
	@Query(nativeQuery=true, value="call sp_report_autoevaluacion(?1)")
	public List<Object[]> reportAutoevaluacion(Integer process);

}
