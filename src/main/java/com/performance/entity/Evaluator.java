package com.performance.entity;

import javax.persistence.Entity;

import com.performance.dto.BaseEntity;

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
	private Integer idEvaluated;
	private Integer idProcess;
	private String companyOpen;
	private String companyChallenging;
	private String companyTrustworthy;
	private String commentaryFinally;
	private Integer calification;
	private Integer progress;
}
