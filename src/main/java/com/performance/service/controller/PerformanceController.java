package com.performance.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.performance.service.dto.DetailEvaluationDTO;
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
	public OperationResponse saveEvaluation(@RequestBody EvaluationDTO dto,@RequestHeader String user){
		
		return evaService.saveEvaluation(dto,user);
	}
	
	@GetMapping("/tracking")
	public PageResponseDTO<TrackingInterface> tracking(	@RequestParam int page, @RequestParam Optional<String> filter,
			@RequestParam int vsize){
		
		return evaService.tracking(filter.orElse(""),page, vsize);
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
	public OperationResponse saveAutoevaluation(@RequestBody EvaluationDTO dto,@RequestHeader String user){
		
		return evatedService.saveAutoEvaluation(dto,user);
	}
	
	@GetMapping("/report/autoevaluacion")
	public void reportAutoevaluacion(	
			@RequestParam Integer report,
			@RequestParam List<String> filters,
			@RequestHeader String user){
		
		evatedService.sendReport(Integer.parseInt(filters.get(0)), report, user, 2434);
	}
	@GetMapping("/report/evaluacion")
	public void reportEvaluacion(	
			@RequestParam Integer report,
			@RequestParam List<String> filters,
			@RequestHeader String user){
		
		evaService.sendReport(Integer.parseInt(filters.get(0)), report, user, 2434);
	}
	
	@GetMapping("/tracking/detail")
	public DetailEvaluationDTO detail(	
			@RequestParam Integer ideva){
		return evaService.getDetail(ideva);
	}
	
	@PutMapping("/tracking/edit")
	public OperationResponse edit(	
			@RequestBody DetailEvaluationDTO dto){
		return evaService.updateEvaluation(dto);
	}
	
	@PostMapping("/tracking/new")
	public OperationResponse save(	
			@RequestBody DetailEvaluationDTO dto){
		return evaService.save(dto);
	}
	
	@GetMapping("/tracking/delete")
	public OperationResponse delete(	
			@RequestParam Integer id){
		return evaService.delete(id);
	}
	
	@GetMapping("/tracking/promedio")
	public void promedio(){
		evaService.promediar();
	}
	
	@GetMapping("/tracking/sendmail")
	public void sendCorreo(){
		evaService.sendMail();
	}
	
}
