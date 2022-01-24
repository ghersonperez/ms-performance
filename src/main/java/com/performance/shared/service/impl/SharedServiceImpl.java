package com.performance.shared.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.performance.shared.dto.DataReport;
import com.performance.shared.dto.MailDTO;
import com.performance.shared.dto.MailMasterDTO;
import com.performance.shared.service.SharedService;








@Service
public class SharedServiceImpl implements SharedService{

	
	
	@Value("${url-mail}")
	String url;
	
	@Value("${url-report}")
	String urlreport;
	
	@Override
	public void sendMail(List<MailDTO> mails) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.add("UNICA-Application", "wappe");
		requestHeaders.add("UNICA-PID", "550e8400-e29b-41d4-a716-446655440000");
		requestHeaders.add("UNICA-User", "admin");
		requestHeaders.add("UNICA-ServiceId", "550e8400-e29b-41d4-a716-446655440000");
		
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(url).encode()
				.toUriString();
		
		
		HttpEntity<MailMasterDTO> entity = new HttpEntity<>(new MailMasterDTO(mails), requestHeaders);
		HttpEntity<Object> response = restTemplate.exchange(urlTemplate, HttpMethod.POST, entity,
				Object.class);
		
		response.getBody();
		
		
	}

	@Override
	public void generateReport(DataReport data) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.add("Ocp-Apim-Subscription-Key", "f60aac663e674ad1a899993ae09c41e9");
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		

		HttpEntity<DataReport> entity = new HttpEntity<>(data, requestHeaders);
		HttpEntity<Void> response = restTemplate.exchange(urlreport, HttpMethod.POST, entity, Void.class);
		
		System.out.println(response.getBody());
		
	}

}
