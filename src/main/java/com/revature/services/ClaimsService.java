package com.revature.services;

import java.util.List;

import com.revature.daos.ClaimDAOImpl;
import com.revature.models.PolicyClaim;



public class ClaimsService {
	private ClaimDAOImpl claimDAO = new ClaimDAOImpl();
	
	public List<PolicyClaim> getClaims(){
		return claimDAO.getClaims();
	}
	
	public void addClaim(PolicyClaim newClaim) {
		claimDAO.addClaim(newClaim);

	}
	
	public  List<PolicyClaim> getClaimsByUser(int user_id){
		return claimDAO.getClaimsByUser(user_id);
	}
	
	public  void updateClaim(String newStatus,int  claim_id) {
		claimDAO.updateClaim(newStatus, claim_id);
	}

}
