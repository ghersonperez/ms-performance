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
public class Evaluated extends BaseEntity {
	
	private String idssff;
	private String name;
	private String emailEvaluated;
	private String gender;
	private String department;
	private String codPo;
	private String dedication;
	private String position;
	private String bu;
	private Integer idProcess;
	private String location;
	private String companyOpen;
	private String companyChallenging;
	private String companyTrustworthy;
	private String commentaryFinally;
	private Integer calification;
	private Boolean finish;
	private Boolean enter;
	private Integer status;

}
