package com.performance.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.performance.service.dto.EvaluationDTO;
import com.performance.service.dto.PerformanceProcessDTO;
import com.performance.service.dto.ProcessTeamDTO;
import com.performance.service.dto.TrackingInterface;
import com.performance.service.services.IEvaluatedService;
import com.performance.service.services.IEvaluatorService;
import com.performance.shared.dto.OperationResponse;
import com.performance.shared.dto.PageResponseDTO;

@RestController()
@RequestMapping("/performance/v1/api")
public class PerformanceController {

	@Autowired
	private IEvaluatorService evaService;
	
	@Autowired
	private IEvaluatedService evatedService;
	
	@GetMapping("/find/team")
	public List<ProcessTeamDTO> findTeam(@RequestHeader String user){
		
		return evaService.getTeams(user);
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
	@GetMapping("/find/myprocess")
	public List<PerformanceProcessDTO> findmyProcess(@RequestHeader String user){
		
		return evatedService.myprocess(user);
	}
	@GetMapping("/find/auto-evaluation")
	public EvaluationDTO findAutoevaluation(@RequestParam Integer id){
		
		return evatedService.searchEvaluation(id);
	}
	
	@PostMapping("/save/autoevaluation")
	public OperationResponse saveAutoevaluation(@RequestBody EvaluationDTO dto){
		
		return evatedService.saveAutoEvaluation(dto);
	}
}
