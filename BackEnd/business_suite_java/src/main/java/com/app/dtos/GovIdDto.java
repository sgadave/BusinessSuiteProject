package com.app.dtos;

import com.app.entities.custom_enum.GovernmentId;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GovIdDto {
	private Long govIdNo;
	private GovernmentId govType;
	public GovIdDto(Long govIdNo, String govType) {
		super();
		this.govIdNo = govIdNo;
		this.govType = GovernmentId.valueOf(govType.toUpperCase());
	}
	public Long getGovIdNo() {
		return govIdNo;
	}
	public void setGovIdNo(Long govIdNo) {
		this.govIdNo = govIdNo;
	}
	public GovernmentId getGovType() {
		return govType;
	}
	public void setGovType(String govType) {
		this.govType = GovernmentId.valueOf(govType.toUpperCase());
	}
	
}
