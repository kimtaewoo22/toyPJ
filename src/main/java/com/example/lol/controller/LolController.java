package com.example.lol.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.lol.service.LolService;
import com.example.lol.vo.SummonerDTO;
	
@Controller
@PropertySource("classpath:application.properties")
public class LolController {

	@Value("${lolApiKey}")
	String lolApiKey;
	
	private final LolService lolService;
	
	public LolController(LolService lolService) {
		this.lolService = lolService;
	}
	/**
	 * 메인페이지 접근
	 * @return	
	 * @throws Exception
	 */
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
		System.out.println("lolInfo() start...............");

		String apiUrl = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/"+gameName+"?api_key="+lolApiKey;
		String result = lolService.restTemple(apiUrl);
		
		SummonerDTO summonerDTO = lolService.summonerDtoReadValue(result);
		
		return summonerDTO;
	}
	
	@ResponseBody
	@RequestMapping(value="/leagueInfo")
	public Object leagueInfo(@RequestParam(value="gameName", required = true) String gameName, HttpServletRequest request) throws Exception{
		System.out.println("leagueInfo() start...............");
		SummonerDTO summonerDTO = lolInfo(gameName);
		
		String id = summonerDTO.getId();
		String apiUrl =  "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+id+"?api_key="+lolApiKey;
		String result = lolService.restTemple(apiUrl);
		
		Object object = lolService.jsonPaser(result);
		
		return object;
	}
}
