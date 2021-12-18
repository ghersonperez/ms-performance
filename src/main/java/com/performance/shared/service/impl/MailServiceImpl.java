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

import com.performance.shared.dto.MailDTO;
import com.performance.shared.dto.MailMasterDTO;
import com.performance.shared.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Value("${url-mail}")
	String url;
	
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

}
