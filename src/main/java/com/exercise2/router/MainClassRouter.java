package com.exercise2.router;

import java.util.List;

import com.exercise2.controller.impl.PlayStationGameControllerImpl;
import com.exercise2.dto.PlayStationGame;
import com.exercise2.service.impl.GameServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.javalin.Javalin;

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
		return gson.toJson(listPlayStationGame);
	}

	private static List<PlayStationGame> feedValuesForGETWithParameters() {
		return gameServiceImpl.getAllGames();
	}

	public static void main(String[] args) {

		new MainClassRouter();

		playStationGameController.init();

		String valueGETWithoutParameters = feedValuesForGETWithNoParamters();
		List<PlayStationGame> valueGETWithParameters = feedValuesForGETWithParameters();

		Javalin app = Javalin.create().start(4567);

		app.get("/games", ctx -> ctx.result(valueGETWithoutParameters));

		app.get("/games/{name}", ctx -> {
			String name = ctx.pathParam("name");
			for (PlayStationGame game : valueGETWithParameters) {
				if (name.equals(game.getTitle())) {
					ctx.result(gson.toJson(game));
					return;
				}
			}
			ctx.status(404);
		});
	}
}
