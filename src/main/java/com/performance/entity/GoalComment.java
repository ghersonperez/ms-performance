package com.performance.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "goals_comment")
public class GoalComment extends BaseEntity {
	
	private Integer idGoal;
	private Integer idEvaluator;
	private String comment;
	

}
