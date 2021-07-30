package com.example.lol.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LolService {
	
	/**
	 * API 호출
	 * @param apiUrl
	 * @return
	 * @throws Exception
	 */
	public String restTemple(String apiUrl) throws Exception{
		String apiResult = "";
		
		RestTemplate restTemplate = new RestTemplate();
		apiResult = restTemplate.getForObject(apiUrl, String.class);
		
		System.out.println("apiResult :::"+apiResult);
		
		return apiResult;
	}
	
}
