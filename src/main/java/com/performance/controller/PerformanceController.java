package com.performance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.performance.dto.EvaluationDTO;
import com.performance.dto.OperationResponse;
import com.performance.dto.PageResponseDTO;
import com.performance.dto.ProcessTeamDTO;
import com.performance.dto.TrackingInterface;
import com.performance.services.IEvaluatorService;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/performance/v1/api")
public class PerformanceController {

	@Autowired
	private IEvaluatorService evaService;
	
	@GetMapping("/find/team")
	public List<ProcessTeamDTO> findTeam(@RequestParam String idssff){
		
		return evaService.getTeams(idssff);
	}
	@GetMapping("/find/evaluation")
	public EvaluationDTO findEvaluation(@RequestParam Integer id){
		
		return evaService.searchEvaluation(id);
	}
	@PostMapping("/save/evaluation")
	public OperationResponse saveEvaluation(@RequestBody EvaluationDTO dto){
		
		return evaService.saveEvaluation(dto);
	}
	
	@GetMapping("/tracking")
	public PageResponseDTO<TrackingInterface> tracking(	@RequestParam int page, 
			@RequestParam int vsize){
		
		return evaService.tracking(page, vsize);
	}
}
