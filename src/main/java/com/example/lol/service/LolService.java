package com.example.lol.service;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lol.vo.SummonerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LolService {
	
	@Value("${lolApiKey}")
	String lolApiKey;
	
	/**
	 * lol 기본정보
	 * @param gameName
	 * @return summonerDTO
	 * @throws Exception
	 */
	public SummonerDTO lolInfo(String gameName) throws Exception{
		String apiUrl = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/"+gameName+"?api_key="+lolApiKey;
		String result = restTemple(apiUrl);
		SummonerDTO summonerDTO = summonerDtoReadValue(result);
		
		return summonerDTO;
	}
	
	/**
	 * 전적 조회
	 * @param gameName
	 * @return 
	 * @throws Exception
	 */
	public Object leagueInfo(String gameName) throws Exception{
		SummonerDTO summonerDTO = lolInfo(gameName);
		
		String id = summonerDTO.getId();
		String apiUrl =  "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+id+"?api_key="+lolApiKey;
		String result = restTemple(apiUrl);
		Object object = jsonPaser(result);
		
		return object;
	}
	
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
