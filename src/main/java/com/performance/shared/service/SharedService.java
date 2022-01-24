package com.performance.shared.service;

import java.util.List;

import com.performance.shared.dto.DataReport;
import com.performance.shared.dto.MailDTO;




public interface SharedService {
	
	void sendMail(List<MailDTO> mails);
	
	void generateReport(DataReport data);


}
