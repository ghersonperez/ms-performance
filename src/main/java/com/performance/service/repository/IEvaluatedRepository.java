package com.performance.service.repository;

import java.util.List;
import java.util.Optional;

import com.performance.service.dto.CalificationCalibrationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.performance.service.entity.Evaluated;



public interface IEvaluatedRepository extends JpaRepository<Evaluated, Integer>{
	
	List<Evaluated> findByEmailEvaluated(String email);
	
	@Query(nativeQuery=true, value="call sp_report_autoevaluacion(?1)")
	public List<Object[]> reportAutoevaluacion(Integer process);
	
	Optional<Evaluated> findByIdssff(String idssff);

	@Query(nativeQuery=true, value="call sp_calification_calibration(?1,?2)")
	public List<CalificationCalibrationProjection> getCalificationCalibration(String idssff,Integer process);

}
