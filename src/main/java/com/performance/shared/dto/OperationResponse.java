package com.performance.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationResponse {
	private boolean status;
	private String message;
	private Integer id;
	private String previous;
	
	public OperationResponse(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public OperationResponse(boolean status, Integer id) {
		super();
		this.status = status;
		this.id = id;
	}
	
	
}
