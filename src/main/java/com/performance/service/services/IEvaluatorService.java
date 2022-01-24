package com.performance.service.services;

import java.util.List;

import com.performance.service.dto.EvaluationDTO;
import com.performance.service.dto.ProcessTeamDTO;
import com.performance.service.dto.TrackingInterface;
import com.performance.shared.dto.OperationResponse;
import com.performance.shared.dto.PageResponseDTO;


public interface IEvaluatorService {
	
	List<ProcessTeamDTO> getTeams(String idssff);
	
	EvaluationDTO searchEvaluation(Integer id);
	
	OperationResponse saveEvaluation(EvaluationDTO dto,String email);
	
	PageResponseDTO<TrackingInterface> tracking(int page,int vsize);
	
	void sendReport(Integer process,Integer idrepo,String email,Integer user);

}
