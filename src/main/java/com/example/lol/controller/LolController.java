package com.example.lol.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.lol.service.LolService;
import com.example.lol.vo.SummonerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
	
@Controller
@PropertySource("classpath:application.properties")
public class LolController {

	@Value("${lolApiKey}")
	String lolApiKey;
	
	private final LolService lolService;
	
	public LolController(LolService lolService) {
		this.lolService = lolService;
	}
	
	@RequestMapping(value="/main")
	public String main() throws Exception{

		return "main";
	}
	/**
	 * 
	 * @param gameName
	 * @param key
	 * @return Summoner 정보 전달
	 * @throws Exception
	 */
	@RequestMapping(value="/lolInfo")
	public SummonerDTO lolInfo(String gameName) throws Exception {
		System.out.println("lol() start...............");
		ObjectMapper objectMapper = new ObjectMapper();
		SummonerDTO summonerDTO = new SummonerDTO();

		String apiUrl = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/"+gameName+"?api_key="+lolApiKey;
		String result = lolService.restTemple(apiUrl);
		System.out.println("reuslt 111111>>>>" + result);
		summonerDTO = objectMapper.readValue(result, SummonerDTO.class);
		System.out.println("reuslt >>>>" + summonerDTO);
		
		return summonerDTO;
	}
	
	@ResponseBody
	@RequestMapping(value="/leagueInfo")
	public Object leagueInfo(@RequestParam(value="gameName", required = true) String gameName, HttpServletRequest request) throws Exception{
		SummonerDTO summonerDTO = lolInfo(gameName);
		String id = summonerDTO.getId();
		String apiUrl =  "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+id+"?api_key="+lolApiKey;
		String result = lolService.restTemple(apiUrl);
		System.out.println("reuslt 111111>>>>" + result);
		
		JSONParser jsonParse = new JSONParser(result);
		Object object = jsonParse.parse();
		
		return object;
	}
}
