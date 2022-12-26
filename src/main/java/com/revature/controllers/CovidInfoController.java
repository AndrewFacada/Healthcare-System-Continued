package com.revature.controllers;

import com.revature.models.CovidInfo;
import com.revature.services.CovidInfoService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class CovidInfoController implements Controller {
	
	private CovidInfoService covidInfoService = new CovidInfoService();
	
	private Handler requestUserCovidInfo = (ctx) -> {
		ctx.json(covidInfoService.getUsersCovidInfoWithId(2));
		ctx.status(200);
	};
	
	private Handler addUserCovidInfo = (ctx) -> {
		CovidInfo newInfo = ctx.bodyAsClass(CovidInfo.class);
		if(covidInfoService.addUsersCovidInfo(newInfo)) {
			ctx.json(covidInfoService.addUsersCovidInfo(newInfo));
			ctx.status(200);
		} else {
			System.out.println("add user covid info went wrong");
			ctx.status(401);
		}
	};
	
	private Handler updateCovidInfo = (ctx) -> {
		CovidInfo newInfo = ctx.bodyAsClass(CovidInfo.class);
		String idString = ctx.pathParam("id");
		
		int id = 1;
		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			ctx.status(422);
			return;
		}
		// SWAP OUT THE OBJECT YA GOTTA CHANGE THE PARAMS
		ctx.json(covidInfoService.updateUserCovidInfo(newInfo.getCovidId(), newInfo.getVaccine_type(), newInfo.getVaccination_date()));  //CHANGE TO AN ENTIRE OBJECT
		ctx.status(200);
		
	};

	@Override
	public void addRoutes(Javalin app) {
		// TODO Auto-generated method stub
		app.get("/covidinfo", requestUserCovidInfo);
		
		app.post("/covidinfo", addUserCovidInfo);
		
		app.patch("/covidinfo/{id}", updateCovidInfo);
		
	}

}
