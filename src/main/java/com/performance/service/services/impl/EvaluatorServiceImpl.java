package com.performance.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.performance.service.dto.EvaluationDTO;
import com.performance.service.dto.GoalCommentDTO;
import com.performance.service.dto.MyTeamDTO;
import com.performance.service.dto.ProcessTeamDTO;
import com.performance.service.dto.TrackingInterface;
import com.performance.service.entity.Evaluated;
import com.performance.service.entity.Evaluator;
import com.performance.service.entity.GoalComment;
import com.performance.service.repository.IEvaluatedRepository;
import com.performance.service.repository.IEvaluatorRepository;
import com.performance.service.repository.IGoalCommentRepository;
import com.performance.service.repository.IGoalRepository;
import com.performance.service.repository.IProcessRepository;
import com.performance.service.services.IEvaluatorService;
import com.performance.shared.dto.MailDTO;
import com.performance.shared.dto.OperationResponse;
import com.performance.shared.dto.PageResponseDTO;
import com.performance.shared.service.MailService;



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
	
	@Autowired
	private MailService mailService;
	
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
				tor.getFinish(),eva.getStatus());
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
			evaluation.setFinish(dto.isTerminated());
			evaluation.setEnter(true);
		
			dto.getGoals().forEach(g->{
				GoalComment comment= commentRepo.findByIdGoalAndIdEvaluator(g.getIdGoal(),dto.getId()).orElse(null);
				 if(comment==null) {
					 commentRepo.save(new GoalComment(g.getIdGoal(), dto.getId(), g.getComment())); 
				 }else {
					 comment.setComment(g.getComment());
					 commentRepo.save(comment);
				 }
			});	
			if(dto.isTerminated()) {
				new Thread(new Runnable() {
					@Override
					public void run() {
							boolean status = true;
							String body;
						List<Evaluator> evalu = evaRepo.findByIdEvaluated(evaluation.getIdEvaluated());
						Evaluated evaluated = evaluatedRepo.findById(evaluation.getIdEvaluated()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Evaluacion no encontrada")  );
						Integer average =  (int) Math.round(evalu.stream().mapToInt(Evaluator::getCalification).average().getAsDouble());
						evaluated.setCalification(average);
						evaluatedRepo.save(evaluated);
						if (evalu.size()>1 ) {
							for(Evaluator e:evalu.stream().filter(c->!Objects.equals(c.getId(), dto.getId())).collect(Collectors.toList())) {
								if(!e.getFinish()) {
									status=false;
									break;
								}
							}
							if(!status) {
									body = "<html>Estimados lideres <br/> <br/> Se le notifica que el l&iacuteder " + evaluation.getNameEvaluator().toUpperCase()
										+ " "
										+ "ha culminado la evaluacion de Performance del colaborador  " + evaluated.getName().toUpperCase() +" <br/> <br/> "
										+ "Ya puede ingresar a evaluarlo haciendo <a href='https://personas-hispam.telefonica.com' >click aqu&iacute.</a> <br/> <br/> <br/>" 
										+ "Un saludo </html>";
								
							}else {
								
							
								body = "<html>Estimados lideres <br/> <br/> Se le notifica que todos los lideres culminaron con la evaluacion del colaborador "
										+ evaluated.getName().toUpperCase()+" <br/> <br/>" 
										+ "El promedio de calificacion es " + average + "<br/> <br/> <br/>"
										+ "Un saludo </html>";
								
							}
							String from = "personas.solucionesdigitales@telefonica.com";
							String subject = "Notificacion del Proceso de Performance";
							MailDTO mail = new MailDTO(from,!status? evalu.stream().filter(c->!c.getFinish()).map(Evaluator::getEmailEvaluator).collect(Collectors.toList()):
								evalu.stream().map(Evaluator::getEmailEvaluator).collect(Collectors.toList()), new ArrayList<>(),
									new ArrayList<>(),subject, body, new ArrayList<>());
							mailService.sendMail(Arrays.asList(mail));
							evaRepo.save(evaluation);
						}
					}
				}).start();
			}
			
			
			
			
			
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