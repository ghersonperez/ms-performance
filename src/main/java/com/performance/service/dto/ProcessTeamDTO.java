package com.performance.service.dto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProcessTeamDTO {
	private int id;
	private String period;
	private String name;
	private Integer status;
	private List<MyTeamDTO> team;
}
