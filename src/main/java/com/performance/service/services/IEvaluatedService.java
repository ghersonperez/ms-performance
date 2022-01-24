package com.performance.service.services;

import java.util.List;

import com.performance.service.dto.EvaluationDTO;
import com.performance.service.dto.PerformanceProcessDTO;
import com.performance.shared.dto.OperationResponse;



public interface IEvaluatedService {
	
	List<PerformanceProcessDTO> myprocess(String idssff);
	
	EvaluationDTO searchEvaluation(Integer id);
	
	OperationResponse saveAutoEvaluation(EvaluationDTO evaluation,String user);
	
	void sendReport(Integer process,Integer idrepo,String email,Integer user);

}
