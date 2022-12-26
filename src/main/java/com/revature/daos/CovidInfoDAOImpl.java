package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.CovidInfo;
import com.revature.utils.ConnectionUtil;

public class CovidInfoDAOImpl implements CovidInfoDAO {

	// get all the covid infos for a user by his ID
	@Override
	public List<CovidInfo> getUserCovidInfoById(int id) {
		try (Connection connection = ConnectionUtil.getConnection()) {		
			String sql = "SELECT covid_id, user_id, vaccine_type, vaccination_date FROM covid_info JOIN user_info USING (user_id) WHERE user_id = " +id+";";
			
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);
			
			
			List<CovidInfo> covidInfos = new ArrayList<>();
			
			if(resultSet.next()) {
				CovidInfo covidInfo = new CovidInfo();
				
				covidInfo.setCovidId(resultSet.getInt("covid_id"));
				covidInfo.setUser_id(resultSet.getInt("user_id"));
				covidInfo.setVaccine_type(resultSet.getString("vaccine_type"));
				covidInfo.setVaccine_type(resultSet.getString("vaccination_date"));	
				
				covidInfos.add(covidInfo);
			}
			return covidInfos;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean addCovidInfo(CovidInfo covidinfo) {
//		public abstract boolean addCovidInfo(CovidInfo covidinfo);
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO covid_info(user_id, vaccine_type, vaccination_date)" +
							"VALUES(?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);

			int index = 1;
			
			statement.setInt(index++, covidinfo.getUser_id());
			statement.setString(index++, covidinfo.getVaccine_type());
			statement.setDate(index++, java.sql.Date.valueOf(covidinfo.getVaccination_date()));
		
			statement.execute();
			
			return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCovidInfo(int covidId, String vaccine_type, String vaccination_date) {
		try(Connection connection = ConnectionUtil.getConnection()) {
		
		String sql = "UPDATE covid_info SET vaccine_type = ?, vaccination_date = ? "+
						"WHERE covid_id = "+covidId+";";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, vaccine_type);
		statement.setDate(2, java.sql.Date.valueOf(vaccination_date));
		statement.execute();
		
		return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteCovidInfoById(int covid_id) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	public static void main(String[] args) {
//	CovidInfoDAO dao = new CovidInfoDAOImpl();
//	System.out.println("Testing CovidInfoDAOImpl");
//	System.out.println(dao.getUserCovidInfoById(2));
//	System.out.println((dao.getUserCovidInfoById(1)).get(0).getVaccine_type()); // works but feels like this should be different
//	System.out.println(dao.getUserCovidInfoById(2));
//	CovidInfo testInfo = new CovidInfo(1, "Moderna", "2021-11-11");
	
//	System.out.println(dao.addCovidInfo(testInfo));
//	System.out.println(dao.updateCovidInfo(1, "Big Moderna", "2016-02-03"));
//}

}
