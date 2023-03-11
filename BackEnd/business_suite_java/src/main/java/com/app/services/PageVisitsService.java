package com.app.services;

import java.util.List;

import com.app.entities.PageVisits;

public interface PageVisitsService {

	void addPageVisits();
	List<PageVisits> getPageVisits();
}
