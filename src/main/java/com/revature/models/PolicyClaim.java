package com.revature.models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PolicyClaim {
	private int claim_id;
	private int user_id;
	private String description;
	private double amount;
	private String status="pending";
	private String submission_date;
	private String decision_date=null;
	
	//only need this constructor because the values are being set instead of fed through as parameters
	public PolicyClaim() {
		LocalDate dateObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = dateObj.format(formatter);
		
		
		this.submission_date =  date; 
	}
	

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public int getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(String submission_date) {
		this.submission_date = submission_date;
	}

	public String getDecision_date() {
		return decision_date;
	}

	public void setDecision_date(String decision_date) {
		this.decision_date = decision_date;
	}
	

//	public static void main(String[] args) {
//		PolicyClaim claim=new PolicyClaim("test",123456,30.00 );
//		System.out.println(claim);
//	}
	

}
