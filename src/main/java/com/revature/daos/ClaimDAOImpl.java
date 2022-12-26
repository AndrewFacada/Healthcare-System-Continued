package com.revature.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.PolicyClaim;
import com.revature.utils.ConnectionUtil;

import java.text.SimpleDateFormat;

public class ClaimDAOImpl implements ClaimDAO {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");

	//maybe add a getClaimsMinusAgent --for agents not to see their own claims
	//essentially the same except sql= "SELECT * FROM claim WHERE NOT user_id= "+ user_id
	
	@Override
	public List<PolicyClaim> getClaims() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			//get list from sql
			String sql = "SELECT * FROM claim";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<PolicyClaim> list = new ArrayList<>();
			
			
			//got through results to get info from claims and make a list from them. 
			while (result.next()) {
				PolicyClaim claim = new PolicyClaim();
				claim.setClaim_id(result.getInt("claim_id"));
				claim.setUser_id(result.getInt("user_id"));
				claim.setDescription(result.getString("claim_description"));
				claim.setAmount(result.getDouble("amount"));
				claim.setStatus(result.getString("status"));
				//these might need to be checked when null
				
				claim.setSubmission_date(result.getString("submission_date"));
				claim.setDecision_date(result.getString("decision_date"));
				
				list.add(claim);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//user_id can be passed in to avoid hard coding one in.
	@Override
	public void addClaim(PolicyClaim newClaim) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			//problem here is getting the user_id without a login for now hard code it
			String sql = "INSERT INTO claim (user_id,claim_description, amount,submission_date) VALUES (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			int index=1;
			statement.setInt(index++, 2);//all claims will have an user_id of 12 CHANGER THIS later!!!
			statement.setString(index++, newClaim.getDescription());
			statement.setDouble(index++, newClaim.getAmount());
			statement.setString(index++, newClaim.getSubmission_date());
			//status, claim_id, and decision_date will be set to default values in postgres
			statement.execute();
			
			//policyClaim's constructor only has 3 parameters

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	
	@Override
	public List<PolicyClaim> getClaimsByUser(int user_id) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			//get list from sql
			String sql = "SELECT * FROM claim WHERE user_id="+user_id;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<PolicyClaim> list = new ArrayList<>();
			
			//got through results to get info from claims and make a list from them. 
			while (result.next()) {
				PolicyClaim claim = new PolicyClaim();
				claim.setClaim_id(result.getInt("claim_id"));
				claim.setUser_id(result.getInt("user_id"));
				claim.setDescription(result.getString("claim_description"));
				claim.setAmount(result.getDouble("amount"));
				claim.setStatus(result.getString("status"));
				//these might need to be checked when null
				claim.setSubmission_date(result.getString("submission_date"));
				claim.setDecision_date(result.getString("decision_date"));
				
				list.add(claim);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateClaim(String newStatus, int claim_id) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE claim SET status=?, decision_date=? WHERE claim_id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newStatus);
			statement.setInt(3, claim_id);
			
			//add a set date for decision date
			
			LocalDate dateObj = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = dateObj.format(formatter);
			statement.setString(2, date);
			statement.execute();
			

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
