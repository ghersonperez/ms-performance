package com.performance.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.performance.dto.EvaluationDTO;
import com.performance.dto.GoalCommentDTO;
import com.performance.dto.MyTeamDTO;
import com.performance.dto.OperationResponse;
import com.performance.dto.PageResponseDTO;
import com.performance.dto.ProcessTeamDTO;
import com.performance.dto.TrackingInterface;
import com.performance.entity.Evaluated;
import com.performance.entity.Evaluator;
import com.performance.entity.GoalComment;
import com.performance.repository.IEvaluatedRepository;
import com.performance.repository.IEvaluatorRepository;
import com.performance.repository.IGoalCommentRepository;
import com.performance.repository.IGoalRepository;
import com.performance.repository.IProcessRepository;
import com.performance.services.IEvaluatorService;

@Service
public class EvaluatorServiceImpl implements IEvaluatorService {

	@Autowired
	private IEvaluatorRepository evaRepo;
	@Autowired
	private IProcessRepository proRepo;
	@Autowired
	private IGoalRepository goalRepo;
	@Autowired
	private IGoalCommentRepository commentRepo;
	@Autowired
	private IEvaluatedRepository evaluatedRepo;
	
	@Override
	public List<ProcessTeamDTO> getTeams(String idssff) {
		List<ProcessTeamDTO> info = new ArrayList<>();
		proRepo.findAll().forEach(c->{
			 info.add(new ProcessTeamDTO(c.getId(), c.getPeriod(), c.getName(), c.getStatus(), 
					 evaRepo.searchTeam(idssff).stream().filter(ev->ev.getProcess()==c.getId()).
					 	map(f-> new MyTeamDTO(f.getId(), f.getIdssff(), f.getName(), f.getPosition(), f.getBu(), f.getGender(), f.getProcess(), 0) )
					 .collect(Collectors.toList())));
		});
		return info;
	}

	@Override
	public EvaluationDTO searchEvaluation(Integer id) {
		Evaluator tor = evaRepo.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Evaluacion no encontrada")  );
		Evaluated eva = evaluatedRepo.findById(tor.getIdEvaluated()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Autoevaluacion no encontrada")  );
		return new EvaluationDTO(tor.getId(), 
				goalRepo.findByIdEvaluated(tor.getIdEvaluated())
											.stream().map(c-> new GoalCommentDTO(c.getId(),c.getGoalDescription(),
													commentRepo.findByIdGoalAndIdEvaluator(c.getId(), tor.getId())
													.orElse(new GoalComment()).getComment(),c.getAutoComment())).collect(Collectors.toList()), 
				tor.getCompanyOpen(), 
				tor.getCompanyChallenging(), 
				tor.getCompanyTrustworthy(), 
				tor.getCommentaryFinally(), 
				eva.getCompanyOpen(), 
				eva.getCompanyChallenging(), 
				eva.getCompanyTrustworthy(), 
				eva.getCommentaryFinally(), 
				tor.getCalification(), 
				tor.getProgress());
	}

	@Override
	public OperationResponse saveEvaluation(EvaluationDTO dto) {
		try {
			Evaluator evaluation = evaRepo.findById(dto.getId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Evaluacion no encontrada")  );
			evaluation.setCompanyOpen(dto.getOpen());
			evaluation.setCompanyChallenging(dto.getChallenging());
			evaluation.setCompanyTrustworthy(dto.getTrust());
			evaluation.setCommentaryFinally(dto.getCommentFinally());
			evaluation.setCalification(dto.getCalification());
			evaluation.setUpdatedAt(new Date());
			evaluation.setUpdatedBy("");
			evaRepo.save(evaluation);
			dto.getGoals().forEach(g->{
				GoalComment comment= commentRepo.findByIdGoalAndIdEvaluator(g.getIdGoal(),dto.getId()).orElse(null);
				 if(comment==null) {
					 commentRepo.save(new GoalComment(g.getIdGoal(), dto.getId(), g.getComment())); 
				 }else {
					 comment.setComment(g.getComment());
					 commentRepo.save(comment);
				 }
			});	
			return new OperationResponse(true, dto.getId());
		} catch (Exception e) {
			return new OperationResponse(true, e.getMessage());
		}
	}

	@Override
	public PageResponseDTO<TrackingInterface> tracking(int page,int vsize) {
		int total =evaRepo.countTracking();
		return  new PageResponseDTO<>(page,vsize,total,evaRepo.tracking(page * vsize, vsize));
	}

}
