package com.example.lol.service;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lol.vo.SummonerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	/**
	 * json 파싱
	 * @param jsonData
	 * @return object
	 * @throws Exception
	 */
	public Object jsonPaser(String jsonData) throws Exception{
		JSONParser jsonParse = new JSONParser(jsonData);
		Object object = jsonParse.parse();
		
		System.out.println("result :::"+object.toString());
		
		return object;
	}
	
	/**
	 * SummonerDTO 객체 바인딩 
	 * @param data
	 * @return summonerDTO
	 * @throws Exception
	 */
	public SummonerDTO summonerDtoReadValue(String data) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		SummonerDTO summonerDTO = objectMapper.readValue(data, SummonerDTO.class);
		
		System.out.println("summonerDTO >>>>" + summonerDTO);
		
		return summonerDTO;
	}
}
