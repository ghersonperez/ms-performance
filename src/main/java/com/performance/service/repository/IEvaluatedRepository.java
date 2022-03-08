package com.performance.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.performance.service.entity.Evaluated;



public interface IEvaluatedRepository extends JpaRepository<Evaluated, Integer>{
	
	List<Evaluated> findByEmailEvaluated(String email);
	
	@Query(nativeQuery=true, value="call sp_report_autoevaluacion(?1)")
	public List<Object[]> reportAutoevaluacion(Integer process);
	
	Optional<Evaluated> findByIdssff(String idssff);
	
	@Query(nativeQuery=true, value="select distinct eva.* from evaluated eva left join evaluator tor on tor.id_evaluated=eva.id where tor.calification>0 and eva.id_process=1")
	List<Evaluated> findAllProcess();

}
