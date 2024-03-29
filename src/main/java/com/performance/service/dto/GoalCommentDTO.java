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
public class GoalCommentDTO {
	
	private Integer idGoal;
	private String goalDescription;
	private String comment;
	private String autoComment;
	List<GoalCommentDTO> comments;

}
