package com.performance.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.performance.dto.EvaluationDTO;
import com.performance.dto.GoalCommentDTO;
import com.performance.dto.OperationResponse;
import com.performance.dto.PerformanceProcessDTO;
import com.performance.entity.Evaluated;
import com.performance.entity.Evaluator;
import com.performance.entity.Goal;
import com.performance.entity.GoalComment;
import com.performance.entity.Process;
import com.performance.repository.IEvaluatedRepository;
import com.performance.repository.IGoalRepository;
import com.performance.repository.IProcessRepository;
import com.performance.services.IEvaluatedService;

@Service
public class EvaluatedServiceImpl implements IEvaluatedService {

	@Autowired
	private IEvaluatedRepository evaluatedRepo;

	@Autowired
	private IProcessRepository processRepo;

	@Autowired
	private IGoalRepository goalRepo;

	@Override
	public List<PerformanceProcessDTO> myprocess(String idssff) {

		return evaluatedRepo.findByIdssff(idssff).stream().map(c -> {
			Process p = processRepo.findById(c.getIdProcess())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proceso no identificado"));
			return new PerformanceProcessDTO(c.getId(), p.getPeriod(), p.getName(), p.isEnable(), p.getStatus());
		}).collect(Collectors.toList());
	}

	@Override
	public EvaluationDTO searchEvaluation(Integer id) {
		Evaluated eva = evaluatedRepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autoevaluacion no encontrada"));
		return new EvaluationDTO(eva.getId(),
				goalRepo.findByIdEvaluated(eva.getId()).stream()
						.map(c -> new GoalCommentDTO(c.getId(), c.getGoalDescription(), c.getAutoComment(), null))
						.collect(Collectors.toList()),
				eva.getCompanyOpen(), eva.getCompanyChallenging(), eva.getCompanyTrustworthy(),
				eva.getCommentaryFinally(), null, null, null, null, null, null);
	}

	@Override
	public OperationResponse saveAutoEvaluation(EvaluationDTO dto) {
		try {
			Evaluated evaluation = evaluatedRepo.findById(dto.getId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"AutoEvaluacion no encontrada")  );
			evaluation.setCompanyOpen(dto.getOpen());
			evaluation.setCompanyChallenging(dto.getChallenging());
			evaluation.setCompanyTrustworthy(dto.getTrust());
			evaluation.setCommentaryFinally(dto.getCommentFinally());
			evaluation.setUpdatedAt(new Date());
			evaluation.setUpdatedBy("");
			evaluatedRepo.save(evaluation);
			dto.getGoals().forEach(g->{
				Goal comment= goalRepo.findById(g.getIdGoal()).orElse(null);
					 comment.setAutoComment(g.getComment());
					 goalRepo.save(comment);
			});	
			return new OperationResponse(true, dto.getId());
		} catch (Exception e) {
			return new OperationResponse(true, e.getMessage());
		}
	}

}
