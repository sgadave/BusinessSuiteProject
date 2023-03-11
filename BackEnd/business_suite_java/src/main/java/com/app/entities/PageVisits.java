package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="page_visits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageVisits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="date")
	private LocalDate date;
	@Column(name="page_visits")
	private int pageVisits;
	public PageVisits(LocalDate date, int pageVisits) {
		super();
		this.date = date;
		this.pageVisits = pageVisits;
	}
	
	
}
