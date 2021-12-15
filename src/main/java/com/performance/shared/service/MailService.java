package com.performance.shared.service;

import java.util.List;

import com.performance.shared.dto.MailDTO;




public interface MailService {
	
	void sendMail(List<MailDTO> mails);

}
