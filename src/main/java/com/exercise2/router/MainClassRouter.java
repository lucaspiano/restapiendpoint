package com.exercise2.router;
import static spark.Spark.get;

import java.util.List;
import java.util.Optional;

import com.exercise2.controller.impl.PlayStationGameControllerImpl;
import com.exercise2.dto.PlayStationGame;
import com.exercise2.service.impl.GameServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.Request;
import spark.Response;
import spark.Route;

public class MainClassRouter {
	
	private static PlayStationGameControllerImpl playStationGameController;
	private static GameServiceImpl gameServiceImpl;
	private static Gson gson;
	
	public MainClassRouter() {
		playStationGameController = new PlayStationGameControllerImpl();
	    gameServiceImpl = GameServiceImpl.getInstance();
	    gson = new GsonBuilder().setPrettyPrinting().create();
	}
		
	private static String feedValuesForGETWithNoParamters() {
		
		List<PlayStationGame> listPlayStationGame = gameServiceImpl.getAllGames();
		String jsonValues = gson.toJson(listPlayStationGame);
		return jsonValues;
	}
	
	private static List<PlayStationGame> feedValuesForGETWithParameters() {
	    List<PlayStationGame> list = gameServiceImpl.getAllGames();
		return list;		
	}
	
	//for the GET request without parameters
	public static void processGETRequestWithNoParamters(String valueGETWithoutParameters) {
		get("/games", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				return valueGETWithoutParameters;
			}
		});
        
	}
	
	//for the GET request with parameters
	private static void processGETRequestWithParamters(List<PlayStationGame> valueGETWithParameters) {
        get("/games/:name", (req,res)-> {
    	    String name = req.params(":name");
        	 
        	for (int i=0; i<valueGETWithParameters.size(); i++) {
        		if (name.equals(valueGETWithParameters.get(i).getTitle())) {
        			String valueReturn = gson.toJson(valueGETWithParameters.get(i));
        			return valueReturn;
        		}
        	}
            return Optional.empty();
        });		
	}
	
	public static void main(String [] args) {
		
		new MainClassRouter();
		
		playStationGameController.init();
		
		String valueGETWithoutParameters = feedValuesForGETWithNoParamters();
				
		List<PlayStationGame> ValueGETWithParameters = feedValuesForGETWithParameters();
		
		processGETRequestWithNoParamters(valueGETWithoutParameters);
				
		processGETRequestWithParamters(ValueGETWithParameters);
    }


}


