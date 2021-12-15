package com.performance.shared.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class MailMasterDTO {
	
	private List<MailDTO> mails;
}
