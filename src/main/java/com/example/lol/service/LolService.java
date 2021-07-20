package com.example.lol.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class LolService {
	
	public Object lolInfo(String lolUrl) throws Exception{
		StringBuilder sb = new StringBuilder();
		
		try {
			URL url = new URL(lolUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.addRequestProperty("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
			con.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko)	 Chrome/91.0.4472.114 Safari/537.36");
			con.addRequestProperty("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			con.addRequestProperty("Origin", "https://developer.riotgames.com");
			
			con.setRequestMethod("GET");
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
				String line;
				while((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println(""+sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb;
	}
	
	public Object jsonParse(String jsonData) throws Exception{
		Object ob = new Object();
		
		return ob;
	}
}
