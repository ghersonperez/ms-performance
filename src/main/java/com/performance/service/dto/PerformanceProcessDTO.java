package com.performance.service.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PerformanceProcessDTO {
	
	private int id;
	private String period;
	private String name;
	private boolean enable;
	private Integer status;

}
