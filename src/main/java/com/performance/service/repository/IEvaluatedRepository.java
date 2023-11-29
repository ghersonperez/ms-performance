package com.performance.service.repository;

import java.util.List;
import java.util.Optional;

import com.performance.service.dto.CalibrationProjection;
import com.performance.service.dto.CalificationResume;
import com.performance.service.dto.CalificationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.performance.service.entity.Evaluated;



public interface IEvaluatedRepository extends JpaRepository<Evaluated, Integer>{
	
	List<Evaluated> findByEmailEvaluated(String email);
	
	@Query(nativeQuery=true, value="call sp_report_autoevaluacion(?1)")
	public List<Object[]> reportAutoevaluacion(Integer process);
	
	Optional<Evaluated> findByIdssff(String idssff);
	
	@Query(nativeQuery=true, value="select  distinct  e.* from evaluated e left join evaluator tor on tor.id_evaluated=e.id where tor.finish =1 and e.id_process =1 and e.status in (0,1)")
	List<Evaluated> findAllProcess();

	@Query(nativeQuery=true, value="call sp_get_calification_all(?1,?2,?3)")
	List<CalibrationProjection> getListCalibration(String calification,String idssffEvaluated,Integer process);

	@Query(nativeQuery=true, value="call sp_table_calibration(?1,?2,?3)")
	List<CalificationTable> getListCalibrationTable(String idssffEvaluated, Integer process, Integer total);

	@Query(nativeQuery=true, value="call sp_get_calification_total(?1,?2)")
	List<CalificationResume> getCalificationResume(String idssffEvaluated, Integer process);



	
	


}
