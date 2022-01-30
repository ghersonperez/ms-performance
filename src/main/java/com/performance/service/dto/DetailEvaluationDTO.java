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
public class DetailEvaluationDTO {
	
	private Integer idEvaluator;
	private String idssffEvaluator;
	private String emailEvaluator;
	private String nameEvaluator;
	private String idssffEvaluated;
	private String nameEvaluated;
	private String genderEvaluated;
	private String departmentEvaluated;
	private String positionEvaluated;
	private String buEvaluated;
	private String locationEvaluated;
	private List<GoalCommentDTO> goals;
	private String emailEvaluated;
	private String codPoEvaluated;
	private String dedicationEva;
	private String codPoEvaluator;
	private String genderEvaluator;
	private String buEvaluator;
	private String departamentEvaluator;
	private String positionEvaluator;
	private String dedicationTor;
	private Integer idprocess;
	   
	


}
