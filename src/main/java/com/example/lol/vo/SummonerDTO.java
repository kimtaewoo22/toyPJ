package com.example.lol.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter

//ignoreUnknown 선언 변수가 없는 제외
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerDTO {
	
	private String accountId;
	private int profileIconId;
	private long revisionDate;
	private String name;
	private String id;
	private String puuid;
	private long summonerLevel;
	
	public SummonerDTO() {
		super();
	}

	public SummonerDTO(String accountId, int profileIconId, long revisionDate, String name, String id, String puuid,
			long summonerLevel) {
		super();
		this.accountId = accountId;
		this.profileIconId = profileIconId;
		this.revisionDate = revisionDate;
		this.name = name;
		this.id = id;
		this.puuid = puuid;
		this.summonerLevel = summonerLevel;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getProfileIconId() {
		return profileIconId;
	}

	public void setProfileIconId(int profileIconId) {
		this.profileIconId = profileIconId;
	}

	public long getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(long revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPuuid() {
		return puuid;
	}

	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}

	public long getSummonerLevel() {
		return summonerLevel;
	}

	public void setSummonerLevel(long summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	
	@Override
	public String toString() {
		return "SummonerDTO [accountId=" + accountId + ", profileconId=" + profileIconId + ", revisionDate="
				+ revisionDate + ", name=" + name + ", id=" + id + ", puuid=" + puuid + ", summonerLevel="
				+ summonerLevel + "]";
	}
	
}
