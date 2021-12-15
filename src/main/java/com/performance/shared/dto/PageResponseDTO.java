package com.performance.shared.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageResponseDTO<T> {
	private int page;
	private int size;
	private int total;
	private List<T> content;
}
