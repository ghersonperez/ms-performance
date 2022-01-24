package com.performance.shared.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataReport {
	
	private List<Object[]> data;
	private Integer report;
	private Integer user;
	private String email;
	private String module;

}
