package com.performance.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import com.performance.service.dto.EvaluationDTO;
import com.performance.service.dto.GoalCommentDTO;
import com.performance.service.dto.PerformanceProcessDTO;
import com.performance.service.entity.Evaluated;
import com.performance.service.entity.Evaluator;
import com.performance.service.entity.Goal;
import com.performance.service.entity.GoalComment;
import com.performance.service.repository.IEvaluatedRepository;
import com.performance.service.repository.IEvaluatorRepository;
import com.performance.service.repository.IGoalCommentRepository;
import com.performance.service.repository.IGoalRepository;
import com.performance.service.repository.IProcessRepository;
import com.performance.service.services.IEvaluatedService;
import com.performance.shared.dto.DataReport;
import com.performance.shared.dto.MailDTO;
import com.performance.shared.dto.OperationResponse;
import com.performance.shared.service.SharedService;
import com.performance.service.entity.Process;

@Service
public class EvaluatedServiceImpl implements IEvaluatedService {

	@Autowired
	private IEvaluatedRepository evaluatedRepo;

	@Autowired
	private IEvaluatorRepository evaluatorRepo;

	@Autowired
	private IProcessRepository processRepo;

	@Autowired
	private IGoalRepository goalRepo;

	@Autowired
	private IGoalCommentRepository commentRepo;

	@Autowired
	private SharedService sharedService;

	@Override
	public List<PerformanceProcessDTO> myprocess(String email) {

		return evaluatedRepo.findByEmailEvaluated(email).stream().map(c -> {
			Process p = processRepo.findById(c.getIdProcess())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proceso no identificado"));
			return new PerformanceProcessDTO(c.getId(), p.getPeriod(), p.getName(), p.isEnable(), p.getStatus());
		}).collect(Collectors.toList());
	}

	@Override
	public EvaluationDTO searchEvaluation(Integer id) {
		Evaluated eva = evaluatedRepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autoevaluacion no encontrada"));
		EvaluationDTO dto = new EvaluationDTO(eva.getId(),
				goalRepo.findByIdEvaluated(eva.getId()).stream()
						.map(c -> new GoalCommentDTO(c.getId(), c.getGoalDescription(), c.getAutoComment(), null, null))
						.collect(Collectors.toList()),
				eva.getCompanyOpen(), eva.getCompanyChallenging(), eva.getCompanyTrustworthy(),
				eva.getCommentaryFinally(), null, null, null, null, null, eva.getFinish(), eva.getStatus(),null,null,null,null,null,eva.getName());

		if (eva.getStatus() == 2) {
			List<GoalCommentDTO> open = new ArrayList<>();
			List<GoalCommentDTO> trust = new ArrayList<>();
			List<GoalCommentDTO> challen = new ArrayList<>();
			List<GoalCommentDTO> fina = new ArrayList<>();

			
			evaluatorRepo.findByIdEvaluated(eva.getId()).forEach(c->{
				if(c.getCompanyOpen()!=null)
				open.add(new GoalCommentDTO(id, c.getNameEvaluator(), c.getCompanyOpen(), null, null));
				if(c.getCompanyTrustworthy()!=null)
				trust.add(new GoalCommentDTO(id, c.getNameEvaluator(), c.getCompanyTrustworthy(), null, null));
				if(c.getCompanyChallenging()!=null)
				challen.add(new GoalCommentDTO(id, c.getNameEvaluator(), c.getCompanyChallenging(), null, null));
				if(c.getCommentaryFinally()!=null)
				fina.add(new GoalCommentDTO(id, c.getNameEvaluator(), c.getCommentaryFinally(), null, null));				
			});
			dto.setCommtOpen(open);
			dto.setCommtTrust(trust);
			dto.setCommtChallen(challen);
			dto.setCommtFinally(fina);
			
			
			dto.getGoals().forEach(com -> {
				List<GoalCommentDTO> coments = commentRepo.findByIdGoal(com.getIdGoal()).stream()
						.map(d -> new GoalCommentDTO(null,
								evaluatorRepo.findById(d.getIdEvaluator()).orElse(new Evaluator()).getNameEvaluator(),
								d.getComment(), null, null))
						.collect(Collectors.toList());
				com.setComments(coments);
				

			});
			dto.setReceipt(eva.getReceipt());
			dto.setCalification(eva.getCalification());
			
		}

		return dto;
	}

	@Override
	public OperationResponse saveAutoEvaluation(EvaluationDTO dto, String user) {
		try {
			Evaluated evaluation = evaluatedRepo.findById(dto.getId()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AutoEvaluacion no encontrada"));
			evaluation.setCompanyOpen(dto.getOpen());
			evaluation.setCompanyChallenging(dto.getChallenging());
			evaluation.setCompanyTrustworthy(dto.getTrust());
			evaluation.setCommentaryFinally(dto.getCommentFinally());
			evaluation.setUpdatedAt(new Date());
			evaluation.setUpdatedBy(user);
			evaluation.setFinish(dto.isTerminated());
			evaluation.setEnter(true);
			evaluation.setReceipt(dto.getReceipt());
			dto.getGoals().forEach(g -> {
				Goal comment = goalRepo.findById(g.getIdGoal()).orElse(null);
				comment.setAutoComment(g.getComment());
				goalRepo.save(comment);
			});
			if(dto.getStatus()==0 || dto.getStatus()==1) {
				if (dto.isTerminated()) {

					new Thread(new Runnable() {
						@Override
						public void run() {
							List<Evaluator> evalu = evaluatorRepo.findByIdEvaluated(evaluation.getId());
							if (!evalu.isEmpty()) {
								String body = "<html>Estimados lideres <br/> <br/> Se le notifica que el/la colaborador/a "
										+ evaluation.getName().toUpperCase() + " "
										+ "ha culminado la autoevaluacion de Performance. "
										+ "Ya puede ingresar a evaluarlo haciendo <a href='https://personas-hispam.telefonica.com' >click aqu&iacute.</a> <br/> <br/> <br/>"
										+ "Un saludo </html>";
								String from = "personas.solucionesdigitales@telefonica.com";
								String subject = "Notificacion del Proceso de Performance";
								MailDTO mail = new MailDTO(from,
										evalu.stream().map(Evaluator::getEmailEvaluator).collect(Collectors.toList()),
										new ArrayList<>(), new ArrayList<>(), subject, body, new ArrayList<>());
								sharedService.sendMail(Arrays.asList(mail));

							}
						}
					}).start();
					evaluation.setStatus(1);
				}
			}if(dto.getStatus()==2) {
				evaluation.setStatus(3);
			}
			evaluatedRepo.save(evaluation);
			return new OperationResponse(true, dto.getId());
		} catch (Exception e) {
			return new OperationResponse(true, e.getMessage());
		}
	}

	public void sendmail(Evaluated evaluation) {
		new Thread(() -> {
			List<Evaluator> evalu = evaluatorRepo.findByIdEvaluated(evaluation.getId());
			if (!evalu.isEmpty()) {
				String body = "<html>Estimados lideres <br/> <br/> Se le notifica que el/la colaborador/a "
						+ evaluation.getName().toUpperCase() + " " + "ha culminado la autoevaluacion de Performance. "
						+ "Ya puede ingresar a evaluarlo haciendo <a href='https://personas-hispam.telefonica.com' >click aqu&iacute.</a> <br/> <br/> <br/>"
						+ "Un saludo </html>";
				String from = "personas.solucionesdigitales@telefonica.com";
				String subject = "Notificacion del Proceso de Performance";
				MailDTO mail = new MailDTO(from,
						evalu.stream().map(Evaluator::getEmailEvaluator).collect(Collectors.toList()),
						new ArrayList<>(), new ArrayList<>(), subject, body, new ArrayList<>());
				sharedService.sendMail(Arrays.asList(mail));

			}
		}).start();
	}

	@Override
	public void sendReport(Integer process, Integer idrepo, String email, Integer user) {

		List<Object[]> data = evaluatedRepo.reportAutoevaluacion(process);
		DataReport info = new DataReport();
		info.setData(data);
		info.setModule("performance");
		info.setReport(idrepo);
		info.setEmail(email);
		info.setUser(user);
		sharedService.generateReport(info);

	}

}
