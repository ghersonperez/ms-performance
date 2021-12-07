package com.performance.services;

import java.util.List;

import com.performance.dto.EvaluationDTO;
import com.performance.dto.OperationResponse;
import com.performance.dto.PerformanceProcessDTO;

public interface IEvaluatedService {
	
	List<PerformanceProcessDTO> myprocess(String idssff);
	
	EvaluationDTO searchEvaluation(Integer id);
	
	OperationResponse saveAutoEvaluation(EvaluationDTO evaluation);

}
