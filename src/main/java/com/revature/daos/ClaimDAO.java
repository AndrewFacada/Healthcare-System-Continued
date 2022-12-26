package com.revature.daos;

import java.util.List;

import com.revature.models.PolicyClaim;

public interface ClaimDAO {

	public abstract List<PolicyClaim> getClaims();
	public abstract void addClaim(PolicyClaim newClaim);
	public abstract List<PolicyClaim> getClaimsByUser(int user_id);
	public abstract void updateClaim(String newStatus,int  claim_id);

}
