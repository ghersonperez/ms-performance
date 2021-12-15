package com.performance.shared.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailContentDTO {
	private String to;
	private String subject;
	private String body;
	private String attach;
}
