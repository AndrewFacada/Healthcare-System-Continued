package com.revature.models;

public class CovidInfo {
	int covidId;
	int user_id;
	String vaccine_type;
	String vaccination_date;
	
	public CovidInfo(int covidId, int user_id, String vaccine_type, String vaccination_date) {
		super();
		this.covidId = covidId;
		this.user_id = user_id;
		this.vaccine_type = vaccine_type;
		this.vaccination_date = vaccination_date;
	}

	public CovidInfo(int user_id, String vaccine_type, String vaccination_date) {
		super();
		this.user_id = user_id;
		this.vaccine_type = vaccine_type;
		this.vaccination_date = vaccination_date;
	}

	public CovidInfo() {
		super();
	}

	public int getCovidId() {
		return covidId;
	}

	public void setCovidId(int covidId) {
		this.covidId = covidId;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getVaccine_type() {
		return vaccine_type;
	}

	public void setVaccine_type(String vaccine_type) {
		this.vaccine_type = vaccine_type;
	}

	public String getVaccination_date() {
		return vaccination_date;
	}

	public void setVaccination_date(String vaccination_date) {
		this.vaccination_date = vaccination_date;
	}
	
	
	
	
}
