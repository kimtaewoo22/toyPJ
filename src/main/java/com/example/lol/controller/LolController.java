package com.example.lol.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * @return Summoner 정보 전달
	 * @throws Exception
	 */
	@RequestMapping(value="/lol")
	public SummonerDTO lol() throws Exception{
		String gameName ="볶음밥쟁이";
		SummonerDTO summonerDTO = lolInfo(gameName);
		
		return summonerDTO;
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
		String result = lolService.lolInfo(apiUrl).toString();
		System.out.println("reuslt 111111>>>>" + result);
		summonerDTO = objectMapper.readValue(result, SummonerDTO.class);
		System.out.println("reuslt >>>>" + summonerDTO);
		
		return summonerDTO;
	}
	
	@RequestMapping(value="/leagueInfo")
	public void leagueInfo() throws Exception{
		SummonerDTO summonerDTO = lol();
		String id = summonerDTO.getId();
		String apiUrl =  "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+id+"?api_key="+lolApiKey;
		String result = lolService.lolInfo(apiUrl).toString();
		System.out.println("reuslt 111111>>>>" + result);
	}
}
