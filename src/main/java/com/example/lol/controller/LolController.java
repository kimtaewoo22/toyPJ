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
	 * lol 기본정보 
	 * @param gameName
	 * @param key
	 * @return Summoner 정보 전달
	 * @throws Exception
	 */
	@RequestMapping(value="/lolInfo")
	public SummonerDTO lolInfo(String gameName) throws Exception {
		System.out.println("lolInfo() start...............");
		return lolService.lolInfo(gameName);
	}
	/**
	 * lol 전적조회 정보 
	 * @param gameName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/leagueInfo")
	public Object leagueInfo(@RequestParam(value="gameName", required = true) String gameName, HttpServletRequest request) throws Exception{
		System.out.println("leagueInfo() start...............");
		return lolService.leagueInfo(gameName);
	}
}
