package com.performance.service.entity;

import javax.persistence.Entity;

import com.performance.shared.dto.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evaluator extends BaseEntity {
	
	private String idssffEvaluator;
	private String nameEvaluator;
	private String emailEvaluator;
	private String codPo;
	private String position;
	private String gender;
	private String bu;
	private String department;
	private String dedication;
	private Integer idEvaluated;
	private Integer idProcess;
	private String companyOpen;
	private String companyChallenging;
	private String companyTrustworthy;
	private String commentaryFinally;
	private Integer calification;
	private Boolean finish;
	private Boolean enter;
}
