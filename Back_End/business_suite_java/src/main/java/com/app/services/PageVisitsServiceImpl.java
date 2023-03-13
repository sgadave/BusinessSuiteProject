package com.app.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.PageVisits;
import com.app.repositories.PageVisitsRepository;

@Service
@Transactional
public class PageVisitsServiceImpl implements PageVisitsService {

	@Autowired
	private PageVisitsRepository pageRepo;

	@Override
	public void addPageVisits() {
		if (LocalDate.now().equals(pageRepo.findTopByOrderByIdDesc().get().getDate())) {
			PageVisits pageVisits = pageRepo.findByDate(LocalDate.now());
			pageVisits.setPageVisits(pageVisits.getPageVisits() + 1);
			pageRepo.save(pageVisits);
		}else {
			PageVisits pageVisits = new PageVisits(LocalDate.now(),1);
			pageRepo.save(pageVisits);
		}

	}

	@Override
	public List<PageVisits> getPageVisits() {
		// TODO Auto-generated method stub
		return pageRepo.findAll();
	}

}
