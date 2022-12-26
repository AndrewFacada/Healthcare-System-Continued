package com.revature.services;

import java.util.List;

import com.revature.daos.CovidInfoDAO;
import com.revature.daos.CovidInfoDAOImpl;
import com.revature.models.CovidInfo;

public class CovidInfoService {
	
	private CovidInfoDAO covidInfoDAO = new CovidInfoDAOImpl();
	
	
	public List<CovidInfo> getUsersCovidInfoWithId(int id) {
		return covidInfoDAO.getUserCovidInfoById(id);
	}
	
	public boolean addUsersCovidInfo(CovidInfo covidInfo) {
		return covidInfoDAO.addCovidInfo(covidInfo);
	}
	
	public boolean updateUserCovidInfo(int covidId, String vaccine_type, String vaccination_date) {
		return covidInfoDAO.updateCovidInfo(covidId, vaccine_type, vaccination_date);
	}
	
}
