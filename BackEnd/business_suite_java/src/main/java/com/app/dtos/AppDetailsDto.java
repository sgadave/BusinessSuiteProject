package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppDetailsDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private Long applicantId;
	private String userName;
}
