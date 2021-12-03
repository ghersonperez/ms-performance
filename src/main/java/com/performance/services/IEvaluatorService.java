package com.performance.services;

import java.util.List;

import com.performance.dto.EvaluationDTO;
import com.performance.dto.OperationResponse;
import com.performance.dto.PageResponseDTO;
import com.performance.dto.ProcessTeamDTO;
import com.performance.dto.TrackingInterface;

public interface IEvaluatorService {
	
	List<ProcessTeamDTO> getTeams(String idssff);
	
	EvaluationDTO searchEvaluation(Integer id);
	
	OperationResponse saveEvaluation(EvaluationDTO dto);
	
	PageResponseDTO<TrackingInterface> tracking(int page,int vsize);

}
