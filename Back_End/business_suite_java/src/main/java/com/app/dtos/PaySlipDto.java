package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySlipDto {

	private Double hra;
	private Double da;
	private Double insurance;
	private Double incentive;
	private Double basicSal;
	private Double tax;

}
