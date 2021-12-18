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
public class MailDTO {
	
	private String from;
	private List<String> to;
	private List<String> cc;
	private List<String> cco;
	private String subject;
	private String body;
	private List<Attachment> attachment;

}
