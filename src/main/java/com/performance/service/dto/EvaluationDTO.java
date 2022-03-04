package com.performance.service.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
	
	private Integer id;
	private List<GoalCommentDTO> goals;
	private String open;
	private String challenging;
	private String trust;
	private String commentFinally;
	private String autoOpen;
	private String autoChallenging;
	private String autoTrust;
	private String autoCommentFinally;
	private Integer calification;
	private boolean terminated;
	private Integer status;
	List<GoalCommentDTO> commtOpen;
	List<GoalCommentDTO> commtChallen;
	List<GoalCommentDTO> commtTrust;
	List<GoalCommentDTO> commtFinally;
	private String receipt;
	private String name;

}
