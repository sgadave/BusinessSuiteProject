package com.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NameAndIdDto {
	private String name;
	private Long id;
	private String status;

	public NameAndIdDto(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public NameAndIdDto(String name, Long id, String status) {
		super();
		this.name = name;
		this.id = id;
		this.status = status;
	}

}
