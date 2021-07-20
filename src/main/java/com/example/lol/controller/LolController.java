package com.example.lol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lol.service.LolService;
import com.example.lol.vo.SummonerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
	
@Controller
public class LolController {
	
	private final LolService lolService;
	
	public LolController(LolService lolService) {
		this.lolService = lolService;
	}
	
	@RequestMapping(value="/lol")
	public String lol() throws Exception{
		String lolApiKey = "RGAPI-7c7eb76c-2e0a-4b8a-8b81-9925be43c9d0";
		String gameName ="볶음밥쟁이";
		lolInfo(gameName,lolApiKey);
		return "test";
	}
	
	@RequestMapping(value="/test")
	public String test() throws Exception{
		
		return "thymeleaf/thymeleafTest";
	}
	
	@RequestMapping(value="/lolInfo")
	public void lolInfo(String gameName, String key) throws Exception {
		System.out.println("lol() start...............");
		ObjectMapper objectMapper = new ObjectMapper();
		SummonerDTO summonerDTO = new SummonerDTO();

		String lolUrl = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/"+gameName+"?api_key="+key;
		String result = lolService.lolInfo(lolUrl).toString();
		System.out.println("reuslt 111111>>>>" + result);
		summonerDTO = objectMapper.readValue(result, SummonerDTO.class);
		System.out.println("reuslt >>>>" + summonerDTO);
	}
}
